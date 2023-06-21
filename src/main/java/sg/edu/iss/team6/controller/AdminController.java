package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminSvc;

    @GetMapping("/admin")
    public String getAdminPage(){
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
        model.addAttribute("admin", admin);
        return "admin-create";
    }

    @PostMapping("admin/create")
    public String saveAdmin(@ModelAttribute("admin") Admin admin, Model model){
        Admin newAdmin = new Admin();

        newAdmin.setFirstName(admin.getFirstName());
        newAdmin.setLastName(admin.getLastName());
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setAddress(admin.getAddress());
        newAdmin.setContactNo(admin.getContactNo());
        newAdmin.setUser(admin.getUser());

        adminSvc.create(newAdmin);
        return "redirect:/admin/list";
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
    public String updateAdmin(@PathVariable("id") Long id, @ModelAttribute("admin") Admin admin) {
        Admin existingAdmin = adminSvc.findById(id);

        existingAdmin.setFirstName(admin.getFirstName());
        existingAdmin.setLastName(admin.getLastName());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setAddress(admin.getAddress());
        existingAdmin.setContactNo(admin.getContactNo());
        existingAdmin.setUser(admin.getUser());

        adminSvc.update(existingAdmin);
        return "redirect:/admin/list";
    }

}