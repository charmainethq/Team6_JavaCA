package sg.edu.iss.team6.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
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

    private Long retrieveLecturerId(HttpSession sessionObj) {
		String lecturerUsername = (String) sessionObj.getAttribute("username");
		Lecturer lecturer = lectSvc.findByUsername(lecturerUsername);
		return lecturer.getLecturerId();
    }
    
    @GetMapping("/lecturer")
    public String lecturerHomePage(HttpSession sessionObj, Model model) {
        return "lecturer";
    }
// Lecturer view courses taught

	@RequestMapping(value = "/lecturer/coursesTaught/", method = RequestMethod.GET)
	public String coursesTaught(HttpSession session, Model model) {
		long lecturerId = retrieveLecturerId(session);
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

// Lecturer view courses enrolled

	@RequestMapping(value = "/lecturer/courseEnrollment/", method = RequestMethod.GET)
	public String courseEnrollmentList(HttpSession session, Model model) {
		long lecturerId = retrieveLecturerId(session);
		Lecturer lecturer = lectSvc.findById(lecturerId);
		ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Course> courseList = new ArrayList<>();
		ArrayList<Enrollment> enrollmentList = new ArrayList<>();

		for (CourseClass courseClass : courseClassList) {
			Course course = cseSvc.findById(courseClass.getCourse().getCourseId());
			courseList.add(course);
			List<Enrollment> enrollments = enrlSvc.findByClassId(courseClass.getClassId());
			for (Enrollment e: enrollments)
				enrollmentList.add(e);

		}
		model.addAttribute(enrollmentList);
		model.addAttribute(lecturer);
		model.addAttribute(courseClassList);
		model.addAttribute(courseList);


		return "lecturer-course-enrollment";
	}
    @RequestMapping(value = "/lecturer/selectCourseAndClass/", method = RequestMethod.GET)
    public String selectCourseAndClass(HttpSession sessionObj, Model model) {
    	long lecturerId = retrieveLecturerId(sessionObj);

        ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
        ArrayList<Course> distinctCourseList = new ArrayList<>();
        for (CourseClass current : courseClassList) {
        	long courseId = current.getCourse().getCourseId();
        	Course course = cseSvc.findById(courseId);
        	if(!distinctCourseList.contains(course)) {
        		distinctCourseList.add(course);
        	}
        }
        model.addAttribute("courseClassList",courseClassList);
        model.addAttribute("courseList", distinctCourseList);
        return "lecturer-select-course-class";
    }

    @RequestMapping(value = "/lecturer/gradeStudentList/{classId}", method = RequestMethod.GET)
    public String viewStudentList(@PathVariable long classId, Model model) {
    	CourseClass courseClass = cseClsSvc.findById(classId);
    	Course course = cseSvc.findById(courseClass.getCourse().getCourseId());
    	ArrayList<Enrollment> enrollmentList = enrlSvc.findByClassId(classId);
    	ArrayList<Enrollment> confirmedEnrollmentList = new ArrayList<>();
    	for(Enrollment enrollment : enrollmentList) {
    		//check enrollment status is only confirmed
    		if(enrollment.getEnrollmentStatus().equals(EnrollmentEnum.CONFIRMED)) {
    			confirmedEnrollmentList.add(enrollment);
    		}
    	}
    	model.addAttribute("courseClass", courseClass);
    	model.addAttribute("course", course);
        model.addAttribute("enrollmentList", confirmedEnrollmentList);
    	return "lecturer-student-list";
    }

// GPA calculator
    private double calculateGpa(long score) {

    	double gpa = 0.0; // < 40

    	if(score >= 80) { // 80 to 100
    		gpa = 5.0;
    	}
    	else if(score >= 75 && score < 80) { // 75 to 79
    		gpa = 4.5;
    	}
    	else if(score >= 70 && score < 75) { // 70 to 74
    		gpa = 4.0;
    	}
    	else if(score >= 65 && score < 70) { // 65 to 69
    		gpa = 3.5;
    	}
    	else if(score >= 60 && score < 65) { // 60 to 64
    		gpa = 3.0;
    	}
    	else if(score >= 55 && score < 60) { // 55 to 59
    		gpa = 2.5;
    	}
    	else if(score >= 50 && score < 55) { // 50 to 54
    		gpa = 2.0;
    	}
    	else if(score >= 45 && score < 50) { // 45 to 49
    		gpa = 1.5;
    	}
    	else { // 40 to 44
    		gpa = 1.0;
    	}
    	return gpa;
    }

// Get the credits of the course
	private int retrieveCourseCredits(long enrollmentId) {
		long classId = enrlSvc.findByEnrollmentId(enrollmentId).getCourseClass().getClassId();
		long courseId = cseClsSvc.findByClassId(classId).getCourse().getCourseId();
		int courseCredits = cseSvc.findById(courseId).getCredits();
		return courseCredits;
	}

// Cumulative GPA calculator
	private double calculateCumulativeGpa(long enrollmentId, double gpa, int credits) {
		long studentId = enrlSvc.findByEnrollmentId(enrollmentId).getStudent().getStudentId();
		double retrieveGpa = stuSvc.findByStudentId(studentId).getGpa();
		
		// student first enrolled course, Cumulative GPA = first course GPA
		if(retrieveGpa == 0.0) { 
			return retrieveGpa = gpa; 
		}
		
		// student has enrolled into other courses before, need to breakdown current Cumulative GPA and calculate new Cumulative GPA
		List<Enrollment> studentEnrolledClass = enrlSvc.findByStudent(stuSvc.findByStudentId(studentId));
		int totalCredits = 0;
		double sumCurrentGpa = 0;
		for(Enrollment enrolled : studentEnrolledClass) {
			if(enrolled.getEnrollmentStatus() == EnrollmentEnum.FAILED || enrolled.getEnrollmentStatus() == EnrollmentEnum.COMPLETED) {
				long currentScore = enrlSvc.findByEnrollmentId(enrolled.getEnrollmentId()).getScore();
				int currentCredits = retrieveCourseCredits(enrolled.getEnrollmentId());
				sumCurrentGpa += calculateGpa(currentScore) * currentCredits; // Sum of GPA * Credits
				totalCredits += currentCredits;
			}
		}
		double cumulativeGpa = (sumCurrentGpa + (gpa * credits) ) / (totalCredits + credits);
		return cumulativeGpa;
	}
	
// Update the database with the input score, calculated GPA and changed enrollment status

	@RequestMapping(value = "/lecturer/gradeStudentList/{enrollmentId}", method = RequestMethod.POST)
	public ModelAndView gradeCourse(@Valid @PathVariable long enrollmentId, @ModelAttribute("enrollment") Enrollment enrollment, BindingResult result) {
		Enrollment currentEnrollment = enrlSvc.findById(enrollmentId);
        ModelAndView modelAndView = new ModelAndView("redirect:/lecturer/gradeStudentList/" + currentEnrollment.getCourseClass().getClassId());
		if(result.hasErrors() || enrollment.getScore() == null) {
        	String message1 = "Invalid input! Please enter a valid score!";
        	modelAndView.addObject("message1", message1);
		}
		else if(enrollment.getScore() < 0 || enrollment.getScore() > 100) {
        	String message2 = "Score out of range! Please enter a range between 0 to 100.";
        	modelAndView.addObject("message2", message2);
        }

		else {
			long score = enrollment.getScore();
			double gpa = calculateGpa(score);
			int credits = retrieveCourseCredits(enrollmentId);
			double cumulativeGpa = calculateCumulativeGpa(enrollmentId, gpa, credits);
			if(score < 40) {
				currentEnrollment.setEnrollmentStatus(EnrollmentEnum.FAILED);
			}
			else {
				currentEnrollment.setEnrollmentStatus(EnrollmentEnum.COMPLETED);
			}
			currentEnrollment.setScore(score);
			enrlSvc.update(currentEnrollment);
			Student student = stuSvc.findByStudentId(currentEnrollment.getStudent().getStudentId());
			student.setGpa(cumulativeGpa);
			stuSvc.update(student);
            String message3 = "Score has been successfully uploaded!";
            modelAndView.addObject("message3", message3);
        } 
        return modelAndView;
	}
	
// Lecturer view student performance list
	

	@GetMapping(value = "/lecturer/performanceList")
	public String studentperformancePage(HttpSession session, Model model) {
		long lecturerId = retrieveLecturerId(session);
		ArrayList<CourseClass> classIdList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Enrollment> enrollmentList = new ArrayList<>();
		for (CourseClass c : classIdList) {			
			
			enrollmentList.addAll(enrlSvc.findByClassId(c.getClassId()));
		}

		ArrayList<Student> stdList = new ArrayList<>();
		ArrayList<Enrollment> updateEnrlList = new ArrayList<>();
		for(Enrollment e : enrollmentList) {
			if(e.getEnrollmentStatus()==EnrollmentEnum.FAILED ||
			   e.getEnrollmentStatus()==EnrollmentEnum.COMPLETED || 
			   e.getEnrollmentStatus()==EnrollmentEnum.CONFIRMED) {
			   updateEnrlList.add(e);
			    	}		
		}

		for (Enrollment current : updateEnrlList) {
			long stdId = current.getStudent().getStudentId();
			boolean isDuplicate = false;

			for (Student std : stdList) {
				if (stdId == std.getStudentId()) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				stdList.add(stuSvc.findByStudentId(stdId));
			}
		}
		model.addAttribute("stdList", stdList);
		return "lecturer-view-std-performance";
	}
	@GetMapping(value="/lecturer/performance/{studentId}")
	public String viewStudentDetails(@PathVariable long studentId, HttpSession session, Model model) {
		long lecturerId = retrieveLecturerId(session);
	    ArrayList<CourseClass> ccList = cseClsSvc.findByLecturerId(lecturerId);
	    ArrayList<Enrollment> enrList = new ArrayList<>();
	    for (CourseClass cc : ccList) {
	        enrList.addAll(enrlSvc.findByClassId(cc.getClassId()));
	    }
	    ArrayList<Enrollment> updatedEnroll= new ArrayList<>();
	    for(Enrollment e : enrList) {
	    	if(e.getStudent().getStudentId()==studentId && 
	    	  (e.getEnrollmentStatus()==EnrollmentEnum.FAILED ||
	    	   e.getEnrollmentStatus()==EnrollmentEnum.COMPLETED || 
	    	   e.getEnrollmentStatus()==EnrollmentEnum.CONFIRMED)) {
	    		updatedEnroll.add(e);
	    	}
	    	
	    }
	    model.addAttribute("enrollList", updatedEnroll);
	    model.addAttribute("student",stuSvc.findByStudentId(studentId));
	    
	    return "lec-view-std-detail";
	}

}