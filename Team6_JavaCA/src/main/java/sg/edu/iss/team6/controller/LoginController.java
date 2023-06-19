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
import sg.edu.iss.team6.service.UserService;

@Controller
public class LoginController {
     
    @Autowired
	private UserService userService;

    @GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, @RequestParam("usertype") String userType, Model model, HttpSession session) {
		String username = user.getUsername();
		boolean isValidUsername = (userType.equals("Admin") && username.startsWith("adm"))
				|| (userType.equals("Lecturer") && username.startsWith("lec"))
				|| (userType.equals("Student") && username.startsWith("stu"));

		if (!isValidUsername) {
			model.addAttribute("error", "Invalid user type");
			return "login";
		}

		if (authenticate(user)) {
			if (userType.equals("Admin")) {
                session.setAttribute("username", username);
				return "adminPage";
			} else if (userType.equals("Lecturer")) {
                session.setAttribute("username", username);
				return "redirect:/lecturer";
			} else if (userType.equals("Student")) {
                session.setAttribute("username", username);
				return "redirect:/student/selfInformation";
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
        return "redirect:/home";
	}
}
