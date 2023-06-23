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
import org.springframework.web.bind.annotation.RequestParam;

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
        String lectuerUsername = (String) sessionObj.getAttribute("username");
        List<Lecturer> lecturerList = lectSvc.findByUser_Username(lectuerUsername);
        long lecturerId = 1; // mock up a lecturer ID
        for(Lecturer lecturer : lecturerList) {
            if(lecturer != null) {
                lecturerId = lecturer.getLecturerId();
            }
        }
        return lecturerId;
    }
    
    @GetMapping("/lecturer")
    public String lecturerHomePage(HttpSession sessionObj, Model model) {
//        String lectuerUsername = (String) sessionObj.getAttribute("username");
//        List<Lecturer> lecturerList = lectSvc.findByUser_Username(lectuerUsername);
//        long lecturerId = 0;
//        for(Lecturer lecturer : lecturerList) {
//            if(lecturer != null) {
//                lecturerId = lecturer.getLecturerId();
//            }
//        }
//        model.addAttribute("lecturerId",lecturerId);
        return "lecturer";
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
// Lecturer Grade A Course

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

	@RequestMapping(value = "/lecturer/gradeStudentList/{enrollmentId}", method = RequestMethod.POST)
    public String gradeStudent(@PathVariable long enrollmentId, @RequestParam("score") long score) {
        if (score < 0 || score > 100) {
            System.out.println("Score out of range !");
        } else if (score < 40) {
            System.out.println("Failed !");
        } else {
            System.out.println("Passed !");
        }
        Enrollment currentEnrollment = enrlSvc.findById(enrollmentId);
        currentEnrollment.setScore(score);
        enrlSvc.update(currentEnrollment);
        long classId = currentEnrollment.getCourseClass().getClassId();
        return "redirect:/lecturer/gradeStudentList/" + classId;
    }
    
}