package sg.edu.iss.team6.controller;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.service.UserService;
import sg.edu.iss.team6.validator.UserValidator;

@Controller
public class UserController{

    @Autowired
    private UserService userSvc;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserValidator userValidator;
  
    //not working so put userValidator.validate(user, bindingResult); in the saveUser
    // @InitBinder
    // private void initUserBinder(WebDataBinder binder) {
    //     binder.setValidator(userValidator);
    // }

    @GetMapping("admin/user/list")
    public String getAllUser(Model model) {
        model.addAttribute("user", userSvc.findAll());
        return "user-list";
    }

    @GetMapping("admin/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username, Model model){
        User user = userSvc.findByUsername(username);
        model.addAttribute("user", user);
        return "user-detail";
    }

    @GetMapping("admin/user/create")
    public String createUser(Model model, User user){
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("admin/user/create")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user-create";
        }
        // Check if the user already exists
        if (userSvc.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.username.exists", "Username already exists");
            return "user-create";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userSvc.create(user);
        return "redirect:/admin/user/list";
    }
    
    @Transactional
    @GetMapping("admin/user/delete/{username}")
    public String deleteUserByUsername(@PathVariable("username") String username) {
        userSvc.delete(username);
        return "redirect:/admin/user/list";
    }

   @GetMapping("admin/user/update/{username}")
    public String showUpdateForm(@PathVariable("username") String username, Model model) {
        User user = userSvc.findByUsername(username);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("admin/user/update/{username}")
    public String updateUser(@PathVariable("username") String username, @ModelAttribute("user") User user) {
        User existingUser = userSvc.findByUsername(username);

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(encoder.encode(user.getPassword()));

        userSvc.update(existingUser);
        return "redirect:/admin/user/list";
    }
}
