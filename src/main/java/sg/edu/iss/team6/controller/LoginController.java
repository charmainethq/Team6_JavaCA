package sg.edu.iss.team6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.service.AdminService;
import sg.edu.iss.team6.service.LecturerService;
import sg.edu.iss.team6.service.StudentService;
import sg.edu.iss.team6.service.UserService;

@Controller
@RequestMapping("/index")
@SessionAttributes(value = {"userSession"}, types = {UserSession.class})
public class LoginController {
    
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;

    @Autowired
    LecturerService lecturerService;


    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model model){

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        User currentUser = userService.findByUsernameAndPassword(name, password);
 
        if(currentUser != null){
            UserSession userSession = new UserSession(currentUser, 
                        studentService.findByuser(currentUser), 
                        lecturerService.findByuser(currentUser), 
                        adminService.findByuser(currentUser));
            model.addAttribute("userSession", userSession);
            if (name.charAt(0) == 'a'){
                return "Admin";
            }
            else if (name.charAt(0) == 'l'){
                return "Lecturer";
            }
            else if (name.charAt(0) == 's'){
                return "Student";
            }
        }

        return "redirect:/index/Error";
    }

     @RequestMapping("/Error")
    public String Error(){
        return "Error";
    }
}
