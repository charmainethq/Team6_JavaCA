package sg.edu.iss.team6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.service.LecturerService;

@Controller
public class LecturerController {
	
	@Autowired
	private LecturerService lecSvc;
	

	@GetMapping("/lecturer/list")
	public String getAllLecturers(Model model){
		List<Lecturer> lecturers = lecSvc.findAll();
		model.addAttribute("lecturers",lecturers);
		return "lecturer-list";
	}
	@GetMapping("lecturer/{id}")
	public String getLecturerById(@PathVariable long id, Model model){
		Lecturer lecturer = lecSvc.findById(id);
		model.addAttribute("lecturer", lecturer);
		return "lecturer-detail";
	}
	
	@GetMapping("lecturer/update/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Lecturer lecturer = lecSvc.findById(id);
		model.addAttribute("lecturer", lecturer);
		return "lecturer-update";
	}
	
	@PostMapping("lecturer/update/{id}")
	public String updateLecturer(@PathVariable("id") long id, @ModelAttribute("lecturer") Lecturer lecturer) {
		Lecturer existingLecturer = lecSvc.findById(id);
		
		existingLecturer.setFirstName(lecturer.getFirstName());
		existingLecturer.setLastName(lecturer.getLastName());
		existingLecturer.setEmail(lecturer.getEmail());
		existingLecturer.setAddress(lecturer.getAddress());
		existingLecturer.setContactNo(lecturer.getContactNo());
		existingLecturer.setUser(lecturer.getUser());
		
		lecSvc.update(existingLecturer);
		return "redirect:/lecturer";
	}
	
	
// FOR FUTURE OPTIONAL IF ADMIN wants to create new lecturer	
//	@GetMapping("/lecturers/new")
//	public String createLecturerForm(Model model) {
//		//create lecturer obj to hold lecturer data
//		Lecturer lec = new Lecturer();
//		model.addAttribute("lecturer",lec);
//		return "create_lecturer";
//	}

}
