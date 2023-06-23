package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.LecturerService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminLecturerController {

	@Autowired
	private LecturerService lectSvc;

	@GetMapping("/admin/lecturer")
	public String getLecturerPage(){
		return "adminPage";
	}
	@GetMapping("/admin/lecturer/list")
	public String getAllLecturer(Model model) {
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
		model.addAttribute("lecturer", lecturer);
		return "lect-create";
	}

	@PostMapping("/admin/lecturer/create")
	public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
            return "lect-create";
        }
		Lecturer newLect = new Lecturer();

		newLect.setFirstName(lecturer.getFirstName());
		newLect.setLastName(lecturer.getLastName());
		newLect.setEmail(lecturer.getEmail());
		newLect.setAddress(lecturer.getAddress());
		newLect.setContactNo(lecturer.getContactNo());
		newLect.setUser(lecturer.getUser());

		lectSvc.create(newLect);
		return "redirect:/admin/lecturer/list";
	}

	@GetMapping("/admin/lecturer/delete/{id}")
	public String deleteLecturerById(@PathVariable(value = "id") Long id) {
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
	public String updateLecturer(@PathVariable("id") Long id, @ModelAttribute("lecturer") Lecturer lecturer) {
		Lecturer existingLecturer = lectSvc.findById(id);

		existingLecturer.setFirstName(lecturer.getFirstName());
		existingLecturer.setLastName(lecturer.getLastName());
		existingLecturer.setEmail(lecturer.getEmail());
		existingLecturer.setAddress(lecturer.getAddress());
		existingLecturer.setContactNo(lecturer.getContactNo());
		existingLecturer.setUser(lecturer.getUser());

		lectSvc.update(existingLecturer);
		return "redirect:/admin/lecturer/list";
	}
}