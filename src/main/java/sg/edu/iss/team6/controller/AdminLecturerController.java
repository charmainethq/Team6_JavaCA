package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.service.*;

import javax.validation.Valid;

@Controller
public class AdminLecturerController {

	@Autowired
	private LecturerService lectSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("/admin/lecturer")
	public String getLecturerPage(){
		return "adminPage";
	}
	@GetMapping("/admin/lecturer/list")
	public String getAllLecturer(Model model) {
		model.addAttribute("userList", userSvc.findAll());
		model.addAttribute("lecturer", lectSvc.findAll());
		return "lect-list";
	}

	@GetMapping("/admin/lecturer/{id}")
	public String getLecturerById(@PathVariable Long id, Model model){
		Lecturer lect = lectSvc.findById(id);
		model.addAttribute("lecturer", lect);
		return "lect-detail";
	}

	@GetMapping("/admin/lecturer/create")
	public String createLecturer(Model model, Lecturer lecturer){
		model.addAttribute("lecUsers", userSvc.findAll());
		model.addAttribute("lecturer", lecturer);
		return "lect-create";
	}

	@PostMapping("/admin/lecturer/create")
	public String saveLecturer(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
							   BindingResult bindingResult,
							   @RequestParam("username") String username,
							   Model model){
		User user = userSvc.findByUsername(username);
		Lecturer existingLecturer = lectSvc.findByUser(user);
		if (existingLecturer != null) {
			model.addAttribute("lecUsers", userSvc.findAll());
			bindingResult.rejectValue("user.username", "error.user.username.alreadyExists",
					"A lecturer has been created under this username.");
			return "lect-create";
		}
		else {
			if (bindingResult.hasErrors()){
				model.addAttribute("lecUsers", userSvc.findAll());
				return "lect-create";
			}
			lecturer.setUser(user);
			lectSvc.create(lecturer);
			return "redirect:/admin/lecturer/list";
		}

	}

	@GetMapping("/admin/lecturer/delete/{id}")
	public String deleteAdminById(@PathVariable(value = "id") Long id) {
		lectSvc.delete(id);
		return "redirect:/admin/lecturer/list";
	}

	@GetMapping("/admin/lecturer/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Lecturer lect  = lectSvc.findById(id);
		model.addAttribute("lecturer", lect);
		return "lect-update";
	}

	@PostMapping("/admin/lecturer/update/{id}")
	public String updateLecturer(@PathVariable("id") Long id,
								 @Valid @ModelAttribute("lecturer") Lecturer lecturer,
								 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Return the same view if there are validation errors
			return "lect-update";
		}
		lectSvc.update(lecturer);
		return "redirect:/admin/lecturer/list";
	}
}