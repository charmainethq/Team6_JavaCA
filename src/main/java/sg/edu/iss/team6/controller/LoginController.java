package sg.edu.iss.team6.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.model.UserSession;
import sg.edu.iss.team6.service.AdminService;
import sg.edu.iss.team6.service.LecturerService;
import sg.edu.iss.team6.service.StudentService;
import sg.edu.iss.team6.service.UserService;

@Controller
public class LoginController {
     
    @Autowired
	private UserService userService;

	@Autowired
    AdminService adminService;
	
	@Autowired
    StudentService studentService;

    @Autowired
    LecturerService lecturerService;

    @GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, @RequestParam("usertype") String userType, Model model, HttpSession session) {
		String username = user.getUsername();
		String password = user.getPassword();

		User currentUser = userService.findByUsernameAndPassword(username, password);

		boolean isValidUsername = (userType.equals("Admin") && username.startsWith("adm"))
				|| (userType.equals("Lecturer") && username.startsWith("lec"))
				|| (userType.equals("Student") && username.startsWith("stu"));

		if (!isValidUsername) {
			model.addAttribute("error", "Invalid user type");
			return "login";
		}

		if (authenticate(user)) {
			UserSession userSession = new UserSession(currentUser, 
                        studentService.findByUser(currentUser), 
                        lecturerService.findByUser(currentUser), 
                        adminService.findByUser(currentUser));
            session.setAttribute("userSession", userSession);

			if (userType.equals("Admin")) {
				return "redirect:/admin";
			} else if (userType.equals("Lecturer")) {
				return "redirect:/lecturer/list";
			} else if (userType.equals("Student")) {
				return "redirect:/student/list";
			} else {
				return "redirect:/login";
			}
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	private boolean authenticate(User user) {
		String enteredUsername = user.getUsername();
		String enteredPassword = user.getPassword();

		User actualUser = userService.findByUsername(enteredUsername);

		if (actualUser != null) {
			String actualPassword = actualUser.getPassword();

			// Compare the entered password with the actual password
			return enteredPassword.equals(actualPassword);
		}

		return false; // User not found
	}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/login";
	}
}
