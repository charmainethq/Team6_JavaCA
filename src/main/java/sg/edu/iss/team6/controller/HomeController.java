package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.UserRepository;
import sg.edu.iss.team6.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, @RequestParam("usertype") String userType, Model model) {

		String username = user.getUsername();
		if (userType.equals("Admin") && !username.startsWith("adm")) {
			model.addAttribute("error", "Invalid username format for Admin");
			return "login";
		}
		if (authenticate(user)) {
			// User authentication successful
			// Add code to set authentication session later

			// Return the appropriate view or redirect based on the user type
			if (userType.equals("Admin")) {
				return "redirect:/admin/list";
			} else if (userType.equals("Lecturer")) {
				return "redirect:/lecturer/list";
			} else if (userType.equals("Student")) {
				return "redirect:/student/list";
			} else {
				// Handle invalid user type
				return "redirect:/login";
			}
		} else {
			// User authentication failed
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	private boolean authenticate(User user) {
		String enteredUsername = user.getUsername();
		String enteredPassword = user.getPassword();

		// Assuming you have a UserRepository or UserService to access the user data
		User actualUser = userService.findByUsername(enteredUsername);

		if (actualUser != null) {
			String actualUsername = actualUser.getUsername();
			String actualPassword = actualUser.getPassword();

			// Compare the entered password with the actual password
			return enteredPassword.equals(actualPassword);
		}

		return false; // User not found
	}





	
    @GetMapping("/home")
	public String getHomePage() {
		return "homePage";
	}
	
    @GetMapping("/welcome")
	public String getWelcomePage() {
		return "welcomePage";
	}
    
	@GetMapping("/admin")
	public String getAdminPage() {
		return "adminPage";
	}
	
	@GetMapping("/lecturer")
	public String getEmployeePage() {
		return "lectPage";
	}
	
	@GetMapping("/student")
	public String getManagerPage() {
		return "studPage";
	}
	
    @GetMapping("/accessDenied")
	public String getAccessDeniedPage() {
		return "accessDeniedPage";
	}
}
