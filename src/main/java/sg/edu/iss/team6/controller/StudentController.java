package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.service.*;
import sg.edu.iss.team6.utility.EmailUtility;

import java.util.*;
import java.util.stream.Collectors;

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

    private static final Long testId = 3L;
    @RequestMapping(value = "/all")
    public @ResponseBody List<String> findAllStudents(){

        List<Student> students = studentService.findAllStudents();
        List<String> studentNames = new ArrayList<>();
        for (Student s: students
             ) {studentNames.add(s.getFirstName());
        }

        return studentNames;
    }

    @GetMapping(value = "/registerCourses")
    public String getUnenrolledAndFailedCourses(Model model) {
        //TODO: remove and use session instead
        Long studentId = testId;

        Student student = studentService.findByStudentId(studentId);
        List<Course> allCourses = courseService.getAllCourses();

        List<Course> unenrolledAndFailedCourses = allCourses.stream()
                .filter(course -> {
                    // filter for enrollment
                    boolean hasEnrollment = student.getStudentEnrollments().stream()
                            .anyMatch(enrollment ->
                                    enrollment.getCourseClass().getCourse().getCourseId() == course.getCourseId());

                    // Check if the enrollment status is withdrawal or failure
                    boolean isWithdrawnOrFailed = student.getStudentEnrollments().stream()
                            .anyMatch(enrollment ->
                                    enrollment.getCourseClass().getCourse().getCourseId() == course.getCourseId() &&
                                            (enrollment.getEnrollmentStatus() == EnrollmentEnum.WITHDRAWN ||
                                                    enrollment.getEnrollmentStatus() == EnrollmentEnum.FAILED));

                    // only include if hasEnrollment is false AND fail/withdraw is true.
                    return !hasEnrollment || isWithdrawnOrFailed;
                })
                .collect(Collectors.toList());
        model.addAttribute("coursesToRegister", unenrolledAndFailedCourses);
        return "student-view-course-registration";
    }





    @GetMapping(value = "{courseId}/viewAvailableClasses")
    public String getClassesByCourseId(@PathVariable("courseId") Long courseId, Model model) {
        //TODO: remove and use session instead
        Student student =  studentService.findByStudentId(testId);
        model.addAttribute("student",student);

        Course course = courseService.findCourseByCourseId(courseId);
        model.addAttribute("course",course);

        List<CourseClass> allClasses = classService.findByCourseId(courseId);
        /**
        List<CourseClass> availableClasses = allClasses.stream()
                .filter(courseClass -> !enrollmentService.hasEnrollment(student.getStudentId(), courseClass.getClassId()))
                .collect(Collectors.toList());

        model.addAttribute("classes", availableClasses);**/


        model.addAttribute("classes", allClasses);

        return "student-classList";
    }

    @PostMapping("/register")
    public String registerClass(@RequestParam("studentId") Long studentId, @RequestParam("classId") Long classId, @RequestParam("courseId") Long courseId) {

        // Retrieve the student and class based on the provided IDs
        Student student = studentService.findByStudentId(studentId);
        CourseClass courseClass = classService.findByClassId(classId);
        Course course = courseService.findCourseByCourseId(courseId);

        //TODO: exceptions
        /**    if (student == null || courseClass == null) {
         // Handle invalid student or class ID
         return "redirect:/student/registerFailure";
         }**/

        // Create a new enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourseClass(courseClass);
        enrollment.setEnrollmentStatus(EnrollmentEnum.SUBMITTED);
        System.out.print(enrollment.getEnrollmentId());

        // Save the enrollment object to the database
        enrollmentRepository.save(enrollment);

        String testRecepientEmail= "sa56team6@outlook.com";

        // Send confirmation email with link
        String confirmationLink = emailUtility.generateConfirmationLink(studentId, enrollment.getEnrollmentId());

        emailService.sendConfirmationEmail(testRecepientEmail, confirmationLink, student.getFullName(), course);

        return "redirect:/student/registerSuccess";
    }


    @GetMapping("/confirmEnrollment")
    public String confirmEnrollment(){
        // TODO
        return "student-registerSuccess";
    }


    @GetMapping("/registerSuccess")
    public String registerSuccess(){
        return "student-registerSuccess";
    }



}
