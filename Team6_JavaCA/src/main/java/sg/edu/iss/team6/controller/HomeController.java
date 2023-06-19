package sg.edu.iss.team6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
	public String getHomePage() {
		return "homePage";
	}
}
