package sg.edu.iss.team6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class HomeController {

	@GetMapping("/")
	public RedirectView redirectToHomePage() {
		return new RedirectView("/home");
	}
    @GetMapping("/home")
	public String getHomePage() {
		return "homePage";
	}



}
