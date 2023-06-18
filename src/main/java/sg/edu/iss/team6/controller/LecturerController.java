package sg.edu.iss.team6.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.UserSession;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.LecturerService;
import sg.edu.iss.team6.service.StudentService;

@RequestMapping("/lecturer")
@Controller
public class LecturerController {
	
	@Autowired
	private LecturerService lecSvc;

	@Autowired
	private StudentService sSvc;
	
	@Autowired
	private CourseClassService ccService;
	
	@Autowired
	private EnrollmentService enrollService;


	@GetMapping("/list")
	public String getAllLecturers(Model model){
		List<Lecturer> lecturers = lecSvc.findAll();
		model.addAttribute("lecturers",lecturers);
		return "lecturer-list";
	}	
	
	@GetMapping("/{id}")
	public String getLecturerById(@PathVariable long id, Model model){
		Lecturer lecturer = lecSvc.findById(id);
		model.addAttribute("lecturer", lecturer);
		return "lecturer-detail";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Lecturer lecturer = lecSvc.findById(id);
		model.addAttribute("lecturer", lecturer);
		return "lecturer-update";
	}
	
	@PostMapping("update/{id}")
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
	
	@GetMapping(value = "/performance/{lecId}")
	public String studentperformancePage(@PathVariable("lecId") long lecId,HttpSession session,  Model model) {
//		UserSession userSession = (UserSession) session.getAttribute("USERSESSION");
//		if (userSession == null || userSession.getLecturer() == null) {
//		    return "redirect:/lecturerlogin";
//		}
	
			//long lecId = userSession.getLecturer().getLecturerId();
		    //Student student = sSvc.findStudentByStudentID(studentId);
		    List<Long> classIdList = ccService.findByLecturerId(lecId);
		    ArrayList<Enrollment> enrollmentList = new ArrayList<>();
		    for(Long c : classIdList) {
		    	enrollmentList=enrollService.findByClassId(c);
		    }
		
	    	ArrayList<Student> stdList = new ArrayList<>();
		
		    for(Enrollment current : enrollmentList) {
			Student eachStudent = sSvc.findStudentByStudentID((current.getStudent().getStudentId()));
			stdList.add(eachStudent);
		}
		
		model.addAttribute("stdList", stdList);
		
		return "lec_stdPerformance";
	}
	
//	@GetMapping("/performance/{id}")
//    public String exampleMethod(@PathVariable("lecturerId") int lecturerId, HttpSession session, Model model) {
//           UserSession userSession = (UserSession) session.getAttribute("USERSESSION");
//		if (userSession == null || userSession.getLecturer() == null) {
//		    return "redirect:/lecturerlogin";
//		}
//
//		
//		List<CourseClass> ccList = ccService.findCourseClassByLecturerId(lecturerId);
//		
//		List<Enrollment> enrollmentList = null;
//		for(CourseClass cc : ccList) {
//			enrollmentList = enrollService.findStudentByClassId(cc.getClassId());
//		}
//		
//		List<Student> studentList = sSvc.findAllStudents();
//		model.addAttribute("stdList",studentList);
//        // Return the view name or redirect URL
//        return "lec_stdPerformance";
//    }
	
	
// FOR FUTURE OPTIONAL IF ADMIN wants to create new lecturer	
//	@GetMapping("/lecturers/new")
//	public String createLecturerForm(Model model) {
//		//create lecturer obj to hold lecturer data
//		Lecturer lec = new Lecturer();
//		model.addAttribute("lecturer",lec);
//		return "create_lecturer";
//	}
	
	
//	@RequestMapping(value = "/performance}", method = RequestMethod.GET)
//	public String studentperformancePage(@PathVariable("studentId") int studentId, HttpSession session, Model model) {
//		UserSession userSession = (UserSession) session.getAttribute("USERSESSION");
//		if (userSession == null || userSession.getLecturer() == null) {
//		    return "redirect:/lecturerlogin";
//		}
//
//		long lecId = userSession.getLecturer().getLecturerId();
//		
//		//List<Student> ccList= ccService.findCourseClassByLecturerId(lecId);
//		List<Student> ccList = new ArrayList();
//		ccList.add(sSvc.findStudentByStudentID(1));
//		
//		
//		model.addAttribute("stdList",ccList);
//		
//
//
//		
//	    return "lec_stdPerformance";
	//}
	
	


}
