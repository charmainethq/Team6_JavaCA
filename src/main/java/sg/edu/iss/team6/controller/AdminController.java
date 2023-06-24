package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.service.AdminService;
import sg.edu.iss.team6.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AdminController{

    @Autowired
    private AdminService adminSvc;

    @Autowired
    private UserService userSvc;

    @GetMapping("/admin")
    public String getAdminPage(HttpSession session, Model model){

        String username= (String)session.getAttribute("username");
        Admin admin = adminSvc.findByUserUsername(username);

        model.addAttribute("name",admin.getFullName());
        return "admin";
    }
    @GetMapping("/admin/list")
    public String getAllAdmin(Model model) {

        model.addAttribute("admin", adminSvc.findAll());
        return "admin-list";
    }

    @GetMapping("/admin/{id}")
    public String getAdminById(@PathVariable Long id, Model model){
        Admin admin = adminSvc.findById(id);
        model.addAttribute("admin", admin);
        return "admin-detail";
    }

    @GetMapping("/admin/create")
    public String createAdmin(Model model, Admin admin){
        model.addAttribute("adminUsers", userSvc.findAll());
        model.addAttribute("admin", admin);
        return "admin-create";
    }

    @PostMapping("admin/create")
    public String saveAdmin(@Valid @ModelAttribute("admin") Admin admin,
                            BindingResult bindingResult,
                            @RequestParam("username") String username,
                            Model model){
        User user = userSvc.findByUsername(username);
        Admin existingAdmin = adminSvc.findByUser(user);
        if (existingAdmin != null) {
            model.addAttribute("adminUsers", userSvc.findAll());
            bindingResult.rejectValue("user.username", "error.user.username.alreadyExists",
                    "An admin has been created under this username.");
            return "admin-create";
        }
        else {
            if (bindingResult.hasErrors()) {
                model.addAttribute("adminUsers", userSvc.findAll());
                return "admin-create";
            }
            admin.setUser(user);
            adminSvc.create(admin);
            return "redirect:/admin/list";
        }
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteAdminById(@PathVariable(value = "id") Long id) {
        adminSvc.delete(id);
        return "redirect:/admin/list";
    }

    @GetMapping("/admin/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Admin admin = adminSvc.findById(id);
        model.addAttribute("admin", admin);
        return "admin-update";
    }

    @PostMapping("/admin/update/{id}")
    public String updateAdmin(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("admin") Admin admin,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Return the same view if there are validation errors
            return "admin-update";
        }
        adminSvc.update(admin);
        return "redirect:/admin/list";
    }

}