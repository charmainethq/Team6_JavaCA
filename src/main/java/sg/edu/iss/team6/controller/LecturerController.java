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

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.LecturerService;
import sg.edu.iss.team6.service.StudentService;

@Controller
public class LecturerController {

	@Autowired
	private LecturerService lectSvc;

	@Autowired
	private EnrollmentService enrlSvc;

	@Autowired
	private CourseService cseSvc;

	@Autowired
	private CourseClassService cseClsSvc;

	@Autowired
	private StudentService stuSvc;

	@GetMapping("/lecturer")
	public String lecturerHomePage(HttpSession sessionObj, Model model) {
		String lectuerUsername = (String) sessionObj.getAttribute("username");
		List<Lecturer> lecturerList = lectSvc.findByUser_Username(lectuerUsername);
		long lecturerId = 0;
		for(Lecturer lecturer : lecturerList) {
			if(lecturer != null) {
				lecturerId = lecturer.getLecturerId();
			}
		}
		model.addAttribute("lecturerId",lecturerId);
		return "lecturer-home-page";
	}

	@GetMapping("/admin/lecturer")
	public String getLecturerPage() {
		return "adminPage";
	}

	@GetMapping("/admin/lecturer/list")
	public String getAllLecturer(Model model) {
		model.addAttribute("lecturer", lectSvc.findAll());
		return "lect-list";
	}

	@GetMapping("/admin/lecturer/{id}")
	public String getLecturerById(@PathVariable int id, Model model) {
		Lecturer lect = lectSvc.findById(id);
		model.addAttribute("lecturer", lect);
		return "lect-detail";
	}

	@GetMapping("/admin/lecturer/create")
	public String createLecturer(Model model, Lecturer lecturer) {
		model.addAttribute("lecturer", lecturer);
		return "lect-create";
	}

	@PostMapping("/admin/lecturer/create")
	public String saveLecturer(@ModelAttribute("lecturer") Lecturer lecturer, Model model) {
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
	public String deleteAdminById(@PathVariable(value = "id") int id) {
		lectSvc.delete(id);
		return "redirect:/admin/lecturer/list";
	}

	@GetMapping("/admin/lecturer/update/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Lecturer lect = lectSvc.findById(id);
		model.addAttribute("lecturer", lect);
		return "lect-update";
	}

	@PostMapping("/admin/lecturer/update/{id}")
	public String updateLecturer(@PathVariable("id") int id, @ModelAttribute("lecturer") Lecturer lecturer) {
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

	@RequestMapping(value = "/lecturer/coursesTaught/{lecturerId}", method = RequestMethod.GET)
	public String coursesTaught(@PathVariable long lecturerId, Model model) {
		Lecturer lecturer = lectSvc.findById(lecturerId);

		List<Long> courseIdList = cseClsSvc.findDistinctCourseId(lecturerId);
		ArrayList<Course> courseList = new ArrayList<>();

		for (long courseId : courseIdList) {
			Course course = cseSvc.findById(courseId);
			courseList.add(course);
		}

		model.addAttribute("lecturer", lecturer);
		model.addAttribute("courseList", courseList);
		return "lecturer-courses-taught";
	}

	@RequestMapping(value = "/lecturer/courseEnrollment/{lecturerId}", method = RequestMethod.GET)
	public String courseEnrollmentList(@PathVariable long lecturerId, Model model) {
		Lecturer lecturer = lectSvc.findById(lecturerId);
		ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Course> courseList = new ArrayList<>();
		ArrayList<Enrollment> enrollmentList = new ArrayList<>();

		for (CourseClass courseClass : courseClassList) {
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
	
	@RequestMapping(value = "/lecturer/classList/{lecturerId}", method = RequestMethod.GET)
	public String viewClassList(@PathVariable long lecturerId, Model model) {
		ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Long> courseIdList = new ArrayList<>();
		ArrayList<Integer> courseNumList = new ArrayList<>();
		ArrayList<String> courseNameList = new ArrayList<>();
		ArrayList<String> courseDescriptionList = new ArrayList<>();

		for (CourseClass current : courseClassList) {
			Course course = cseSvc.findById(current.getCourse().getCourseId());
			courseIdList.add(course.getCourseId());
			courseNumList.add(course.getCourseNum());
			courseNameList.add(course.getName());
			courseDescriptionList.add(course.getDescription());
		}

		model.addAttribute("courseClassList", courseClassList);
		model.addAttribute("courseIdList", courseIdList);
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("courseNameList", courseNameList);
		model.addAttribute("courseDescriptionList", courseDescriptionList);
		return "lecturer-class-list";
	}

	@RequestMapping(value = "/lecturer/class/{classId}", method = RequestMethod.GET)
	public String enrollmentList(@PathVariable long classId, Model model) {
		ArrayList<Enrollment> enrollmentList = enrlSvc.findByClassId(classId);
		ArrayList<String> studentFirstName = new ArrayList<>();
		ArrayList<String> studentLastName = new ArrayList<>();
		Course courseGet = null;

		for (Enrollment current : enrollmentList) {
			Student student = stuSvc.findById(current.getStudent().getStudentId());
			if (courseGet == null) {
				Course course = cseSvc.findById(current.getCourseClass().getCourse().getCourseId());
				courseGet = course;
			}
			studentFirstName.add(student.getFirstName());
			studentLastName.add(student.getLastName());
		}

		model.addAttribute("enrollmentList", enrollmentList);
		model.addAttribute("course", courseGet);
		model.addAttribute("firstName", studentFirstName);
		model.addAttribute("lastName", studentLastName);
		return "lecturer-class-view";
	}

	@RequestMapping(value = "/lecturer/grade/{enrollmentId}", method = RequestMethod.GET)
	public String showGradeCourse(@PathVariable long enrollmentId, Model model) {
		Enrollment enrollment = enrlSvc.findById(enrollmentId);
		Course course = cseSvc.findById(enrollment.getCourseClass().getCourse().getCourseId());
		CourseClass courseClass = cseClsSvc.findById(enrollment.getCourseClass().getClassId());
		Student student = stuSvc.findById(enrollment.getStudent().getStudentId());

		model.addAttribute("enrollment", enrollment);
		model.addAttribute("course", course);
		model.addAttribute("courseClass", courseClass);
		model.addAttribute("student", student);

		return "lecturer-grade-student";
	}

	@RequestMapping(value = "/lecturer/grade/{enrollmentId}", method = RequestMethod.POST)
	public String gradeCourse(@PathVariable long enrollmentId, @ModelAttribute("enrollment") Enrollment enrollment) {
		if (enrollment.getScore() == null) {
			System.out.println("Score cannot by empty");
		} else if (enrollment.getScore() > 100 || enrollment.getScore() < 0) {
			System.out.println("Score out of range !");
		} else {
			System.out.println("Score updated successfully !");
		}
		Enrollment currentEnrollment = enrlSvc.findById(enrollmentId);
		currentEnrollment.setScore(enrollment.getScore());
		enrlSvc.update(currentEnrollment);
		long classId = currentEnrollment.getCourseClass().getClassId();
		return "redirect:/lecturer/class/" + classId;
	}
}
