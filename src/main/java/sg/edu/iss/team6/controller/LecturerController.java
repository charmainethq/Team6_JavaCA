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
    @RequestMapping(value = "/lecturer/courseList/{lecturerId}", method = RequestMethod.GET)
    public String viewCourseList(@PathVariable long lecturerId, Model model) {
        ArrayList<CourseClass> courseClassList = cseClsSvc.findByLecturerId(lecturerId);
        ArrayList<Course> courseList = new ArrayList<>();
        for (CourseClass current : courseClassList) {
        	long courseId = current.getCourse().getCourseId();
        	Course course = cseSvc.findById(courseId);
        	if(!courseList.contains(course)) {
        		courseList.add(course);
        	}
        }
        model.addAttribute("courseList", courseList);
        return "lecturer-course-list";
    }

    @RequestMapping(value = "/lecturer/classList/{courseId}", method = RequestMethod.GET)
    public String viewClassList(@PathVariable long courseId, Model model) {
    	Course course = cseSvc.findById(courseId);
    	ArrayList<CourseClass> classList = cseClsSvc.findByCourseId(courseId);
    	model.addAttribute("course", course);
        model.addAttribute("classList", classList);
        return "lecturer-class-list";
    }

    @RequestMapping(value = "/lecturer/studentList/{classId}", method = RequestMethod.GET)
    public String viewStudentList(@PathVariable long classId, Model model) {
    	CourseClass courseClass = cseClsSvc.findById(classId);
    	Course course = cseSvc.findById(courseClass.getCourse().getCourseId());
    	ArrayList<Enrollment> enrollmentList = enrlSvc.findByClassId(classId);
    	model.addAttribute("courseClass", courseClass);
    	model.addAttribute("course", course);
        model.addAttribute("enrollmentList", enrollmentList);
    	return "lecturer-student-list";
    }
    
    @RequestMapping(value = "/lecturer/gradeStudent/{enrollmentId}", method = RequestMethod.GET)
    public String showGradeCourse(@PathVariable long enrollmentId, Model model) {
        Enrollment enrollment = enrlSvc.findById(enrollmentId);
        CourseClass courseClass = cseClsSvc.findById(enrollment.getCourseClass().getClassId());
        Course course = cseSvc.findById(enrollment.getCourseClass().getCourse().getCourseId());
        Student student = stuSvc.findByStudentId(enrollment.getStudent().getStudentId());

        model.addAttribute("enrollment", enrollment);
        model.addAttribute("course", course);
        model.addAttribute("courseClass", courseClass);
        model.addAttribute("student", student);

        return "lecturer-grade-student";
    }

    @RequestMapping(value = "/lecturer/gradeStudent/{enrollmentId}", method = RequestMethod.POST)
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
        return "redirect:/lecturer/studentList/" + classId;
    }

	@GetMapping(value = "/lecturer/performanceList/{lecturerId}")
	public String studentperformancePage(@PathVariable long lecturerId, Model model) {
		ArrayList<CourseClass> classIdList = cseClsSvc.findByLecturerId(lecturerId);
		ArrayList<Enrollment> enrollmentList = new ArrayList<>();
		for (CourseClass c : classIdList) {
			enrollmentList.addAll(enrlSvc.findByClassId(c.getClassId()));
		}

		ArrayList<Student> stdList = new ArrayList<>();

		for (Enrollment current : enrollmentList) {
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
	    //long lecturerId = (Long) session.getAttribute("lecturerId");
        String lectuerUsername = (String) session.getAttribute("username");
        List<Lecturer> lecturerList = lectSvc.findByUser_Username(lectuerUsername);
        long lecturerId = 0;
        for(Lecturer lecturer : lecturerList) {
            if(lecturer != null) {
                lecturerId = lecturer.getLecturerId();
            }
        }
	    ArrayList<CourseClass> ccList = cseClsSvc.findByLecturerId(lecturerId);
	    ArrayList<Enrollment> enrList = new ArrayList<>();
	    for (CourseClass cc : ccList) {
	        enrList.addAll(enrlSvc.findByClassId(cc.getClassId()));
	    }
	    ArrayList<Enrollment> updatedEnroll= new ArrayList<>();
	    for(Enrollment e : enrList) {
	    	if(e.getStudent().getStudentId()==studentId) {
	    		updatedEnroll.add(e);
	    	}
	    	
	    }
	    model.addAttribute("enrollList", updatedEnroll);
	    model.addAttribute("student",stuSvc.findByStudentId(studentId));
	    
	    return "lec-view-std-detail";
	}


}