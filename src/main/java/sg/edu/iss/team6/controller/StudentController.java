package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import sg.edu.iss.team6.controller.exception.ResourceNotFoundException;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.service.*;
import sg.edu.iss.team6.utility.EmailUtility;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseClassService classService;

    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    CourseService courseService;

    @Autowired
    EmailService emailService;
    @Autowired
    EmailUtility emailUtility;

    /**
    //for testing only
    private Student student;
    @PostConstruct
    public void init() {
        student = studentService.findByUserUsername("stu_3_charlie");
    }**/


    @GetMapping
    public String homePage(HttpSession session, Model model){

        String username= (String)session.getAttribute("username");
        Student student = studentService.findByUserUsername(username);

        model.addAttribute("name",student.getFullName());
        return "student";
    }

    @GetMapping(value = "/registerCourses")
    public String listCoursesForRegistration(HttpSession session, Model model) {
        String username= (String)session.getAttribute("username");
        Student student = studentService.findByUserUsername(username);
        List<Enrollment> enrollments = enrollmentService.findByStudent(student);
        List<Course> allCourses = courseService.getAllCourses();

        Map<Long, Boolean> canRegister = new HashMap<>();

        //set default value to can register to avoid nulls
        for (Course course : allCourses) {
            canRegister.put(course.getCourseId(), true);
        }

        //set to false if student has completed, attempted to register or been removed.
        for (Enrollment enrollment : enrollments) {
            Course course = enrollment.getCourseClass().getCourse();
            if (enrollment.getEnrollmentStatus().equals(EnrollmentEnum.COMPLETED)
                    || enrollment.getEnrollmentStatus().equals(EnrollmentEnum.REMOVED)
                    || enrollment.getEnrollmentStatus().equals(EnrollmentEnum.SUBMITTED)) {
                canRegister.put(course.getCourseId(), false);
            }
        }

        model.addAttribute("courses", allCourses);
        model.addAttribute("canRegister", canRegister);


        return "student-view-course-registration";
    }



    @GetMapping(value = "viewClasses/{courseId}")
    public String getClassesByCourseId(@PathVariable("courseId") Long courseId,
                                       HttpSession session,
                                       Model model) {

        String username= (String)session.getAttribute("username");
        Student student= studentService.findByUserUsername(username);
        Course course = courseService.findCourseByCourseId(courseId);
        List<CourseClass> allClasses = classService.findByCourseId(courseId);

        List<String> lecturerNames = allClasses.stream()
                .map(courseClass -> courseClass.getLecturer().getFullName())
                .collect(Collectors.toList());

        if (student == null || allClasses == null || course == null || lecturerNames == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        model.addAttribute("student",student);
        model.addAttribute("course",course);
        model.addAttribute("classes", allClasses);
        model.addAttribute("lecturerNames", lecturerNames);

        return "student-view-classes";
    }

    @PostMapping("/register")
    public String registerClass(@RequestParam("studentId") Long studentId, @RequestParam("classId") Long classId, @RequestParam("courseId") Long courseId, HttpSession session, Model model) {


        // Retrieve the student and class based on the provided IDs
        String username= (String)session.getAttribute("username");
        Student student= studentService.findByUserUsername(username);
        CourseClass courseClass = classService.findByClassId(classId);
        Course course = courseService.findCourseByCourseId(courseId);

        if (student == null || courseClass == null || course == null) {
            throw new ResourceNotFoundException("Resource not found");
        }

        // Find the current enrollment status if any
        Enrollment existingEnrollment = enrollmentService.findByStudentAndClass(classId,studentId).orElse(null);

        // Reject if completed or attempted to register before
        if (existingEnrollment != null &&
                (existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.SUBMITTED
                        || existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.CONFIRMED
                        || existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.COMPLETED
                        || existingEnrollment.getEnrollmentStatus() ==EnrollmentEnum.REMOVED
                )) {
            model.addAttribute("eStatus", existingEnrollment.getEnrollmentStatus().toString());
            System.out.println(existingEnrollment.getEnrollmentStatus());

            return "student-register-fail";
        }

        // Else create a new enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourseClass(courseClass);
        enrollment.setEnrollmentStatus(EnrollmentEnum.SUBMITTED);
        System.out.print(enrollment.getEnrollmentId());

        enrollmentRepository.save(enrollment);


        // Send confirmation email with link
        String testRecepientEmail= "sa56team6@outlook.com";

        String confirmationLink = emailUtility.generateConfirmationLink(studentId, classId);
        emailService.sendConfirmationEmail(testRecepientEmail, confirmationLink, student.getFullName(), course);

        return "redirect:/student/registerSuccess";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess(){
        return "student-register-success";
    }

    @GetMapping("/confirmEnrollment")
    public String createEnrollmentFromUrl(@RequestParam("studentId") Long studentId, @RequestParam("classId") Long classId, Model model) {
        Enrollment enrollment = enrollmentService.findByStudentAndClass(classId,studentId).orElse(null);

        if (enrollment== null) {
            throw new ResourceNotFoundException("Resource not found");
        }
        enrollmentService.updateEnrollmentStatus(enrollment.getEnrollmentId(), EnrollmentEnum.CONFIRMED);
        String courseName = enrollment.getCourseClass().getCourse().getCourseNum() + " " + enrollment.getCourseClass().getCourse().getName();
        model.addAttribute("courseName", courseName);
        return "student-enrollment-success";
    }



    @GetMapping("/selfInformation")
    public String selfInformation(HttpSession session,Model model){
        
        String username= (String)session.getAttribute("username");
        Student curntStudent= studentService.findByUserUsername(username);


        model.addAttribute("curntStudent",curntStudent);
        
        return "stu-Information";
    }

    @GetMapping("/enrollingCourses")
    public String enrollingCourses(HttpSession session,Model model){

        String username= (String)session.getAttribute("username");
        Student curntStudent= studentService.findByUserUsername(username);

        Map<String, Long> courseAndscore = studentService.getCourseandScore(curntStudent.getStudentId());

        model.addAttribute("courseAndscore", courseAndscore);
        model.addAttribute("curntStudent", curntStudent);
        model.addAttribute("gpa",studentService.computeStudentgpa(curntStudent.getStudentId()));
        return "stu-classlist";
    }

}
