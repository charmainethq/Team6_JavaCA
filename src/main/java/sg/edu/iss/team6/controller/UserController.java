package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.service.UserService;

@Controller
public class UserController{

    @Autowired
    private UserService userSvc;

    @GetMapping("/user")
    public String getAllUser(Model model) {
        model.addAttribute("user", userSvc.findAll());
        return "user-list";
    }

    @GetMapping("/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username, Model model){
        User user = userSvc.findByUsername(username);
        model.addAttribute("user", user);
        return "user-detail";
    }

    @GetMapping("/user/create")
    public String createUser(Model model, User user){
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("user/create")
    public String saveUser(@ModelAttribute("admin") User user, Model model){
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        userSvc.create(newUser);
        return "redirect:/user";
    }

    @Transactional
    @GetMapping("/user/delete/{username}")
    public String deleteUserByUsername(@PathVariable("username") String username) {
        userSvc.delete(username);
        return "redirect:/user";
    }

    @GetMapping("/user/update/{username}")
    public String showUpdateForm(@PathVariable("username") String username, Model model) {
        User user = userSvc.findByUsername(username);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user/update/{username}")
    public String updateUser(@PathVariable("username") String username, @ModelAttribute("user") User user) {
        User existingUser = userSvc.findByUsername(username);

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        userSvc.update(existingUser);
        return "redirect:/user";
    }
}
