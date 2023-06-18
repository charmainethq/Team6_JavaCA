package sg.edu.iss.team6.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
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
	private EnrollmentService enrlSvc;
	
	@Autowired
	private CourseService cseSvc;
	
	@Autowired
	private CourseClassService cseClsSvc;
	
	@Autowired
	private StudentService stuSvc;
	
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
	
	@PostMapping("/update/{id}")
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
	
	@RequestMapping(value="coursesTaught/{lecturerId}", method=RequestMethod.GET)
	public String coursesTaught(@PathVariable long lecturerId, Model model)
	{
		Lecturer lecturer = lecSvc.findById(lecturerId);
		
		List<Long> courseIdList = cseClsSvc.findDistinctCourseId(lecturerId);
		ArrayList<Course> courseList = new ArrayList<>();
		
		for(long courseId : courseIdList) {
			Course course = cseSvc.findById(courseId);
			courseList.add(course);
		}
		
		model.addAttribute("lecturer", lecturer);
		model.addAttribute("courseList", courseList);
		return "lecturer-courses-taught";
	}
	
	@RequestMapping(value="courseEnrollment/{lecturerId}", method=RequestMethod.GET)
	public String courseEnrollmentList(@PathVariable long lecturerId, Model model) {
		Lecturer lecturer = lecSvc.findById(lecturerId);
		ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Course> courseList = new ArrayList<>();
		ArrayList<Enrollment> enrollmentList = new ArrayList<>();
		
		for(CourseClass courseClass : courseClassList) {
			Course course = cseSvc.findById(courseClass.getCourse().getCourseId());
			courseList.add(course);
			Enrollment enrollment = enrlSvc.findById(courseClass.getClassId());
			enrollmentList.add(enrollment);
		}
		
		model.addAttribute(lecturer);
		model.addAttribute(courseClassList);
		model.addAttribute(courseList);
		model.addAttribute(enrollmentList);
		return "lecturer-course-enrollment";
	}
	
	@RequestMapping(value="gradeList/{classId}", method=RequestMethod.GET)
	public String enrollmentList(@PathVariable long classId, Model model) {
		ArrayList<Enrollment> enrollmentList = enrlSvc.findByClassId(classId);
		ArrayList<String> studentFirstName = new ArrayList<>();
		ArrayList<String> studentLastName = new ArrayList<>();
		Course courseGet = null;
		
		for(Enrollment current : enrollmentList) {
			Student student = stuSvc.findById(current.getStudent().getStudentId());
			if(courseGet == null) {
				Course course = cseSvc.findById(current.getCourseClass().getCourse().getCourseId());
				courseGet = course;
			}
			studentFirstName.add(student.getFirstName());
			studentLastName.add(student.getLastName());
		}
		
		model.addAttribute("enrollmentList",enrollmentList);
		model.addAttribute("course", courseGet);
		model.addAttribute("firstName",studentFirstName);
		model.addAttribute("lastName",studentLastName);
		return "lecturer-class-list-grade";
	}
	
	@RequestMapping(value="grade/{enrollmentId}", method=RequestMethod.GET)
	public String showGradeCourse(@PathVariable long enrollmentId, Model model) {
		Enrollment enrollment = enrlSvc.findById(enrollmentId);
		Course course =  cseSvc.findById(enrollment.getCourseClass().getCourse().getCourseId());
		Student student = stuSvc.findById(enrollment.getStudent().getStudentId());
		
		model.addAttribute("enrollment", enrollment);
		model.addAttribute("course", course);
		model.addAttribute("student", student);
	
		return "lecturer-grade-student";
	}
	
	@RequestMapping(value="grade/{enrollmentId}", method=RequestMethod.POST)
	public String gradeCourse(@PathVariable long enrollmentId ,@ModelAttribute("enrollment") Enrollment enrollment) {
		if(enrollment.getScore() == null) {
			System.out.println("Score cannot by empty");
		}
		else if(enrollment.getScore() > 100 || enrollment.getScore() < 0) {
			System.out.println("Score out of range !");
		}
		else {
			System.out.println("Score updated successfully !");
		}
		Enrollment currentEnrollment = enrlSvc.findById(enrollmentId);
		currentEnrollment.setScore(enrollment.getScore());
		enrlSvc.update(currentEnrollment);
		long classId = currentEnrollment.getCourseClass().getClassId();
		return "redirect:/lecturer/gradeList/" + classId;
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
