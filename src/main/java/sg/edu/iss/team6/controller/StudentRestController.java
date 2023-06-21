package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.service.*;
import sg.edu.iss.team6.utility.EmailUtility;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/student")
public class StudentRestController {
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
    String username = "stu_3_charlie";
    @GetMapping
    public String homePage(HttpSession session, Model model){

        String username= (String)session.getAttribute("username");
        Student student = studentService.findByUserUsername(username);

        model.addAttribute("name",student.getFullName());
        return "student";
    }

    @GetMapping(value = "/registerCourses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Long, Boolean>> getEligibility(HttpSession session) {
        try {
            //String username= (String)session.getAttribute("username");

            Student student = studentService.findByUserUsername(username);
            List<Enrollment> enrollments = enrollmentService.findByStudent(student);
            List<Course> allCourses = courseService.getAllCourses();

            if (allCourses == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
            return new ResponseEntity<>(canRegister, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/fetchAllCourses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @GetMapping(value = "/fetchClasses/{courseId}")
    public ResponseEntity<List<CourseClass>> getClassesByCourseId(
            @PathVariable("courseId") Long courseId) {

        try {
            List<CourseClass> classes = classService.findByCourseId(courseId);
            return ResponseEntity.ok(classes);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/fetchLecturerNames/{courseId}")
    public ResponseEntity<List<String>> getLecturerNamesByCourseId(
            @PathVariable("courseId") Long courseId) {

        try {
            List<CourseClass> classes = classService.findByCourseId(courseId);
            List<String> lecturerNames = classes.stream()
                    .map(courseClass -> courseClass.getLecturer().getFullName())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(lecturerNames);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/fetchCourse/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getClassByCourseId(@PathVariable("courseId") Long courseId) {
        Course course = courseService.findCourseByCourseId(courseId);
        if (course == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(course);
    }


    @GetMapping(value = "/fetchStudent")
    public ResponseEntity<Student> getStudentFromSession() {
        Student student = studentService.findByStudentId(testId);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(student);
    }


    @PostMapping(value = "/register/{classId}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> registerClass(@PathVariable("classId") Long classId, @PathVariable("courseId") Long courseId) {

        boolean success = true;

        // TODO: Replace with session
        Long studentId = testId;
        Student student = studentService.findByStudentId(studentId);

        CourseClass courseClass = classService.findByClassId(classId);
       //Course course = courseService.findCourseByCourseId(courseId);

        if (student == null || courseClass == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        // Find the current enrollment status if any
        Enrollment existingEnrollment = enrollmentService.findByStudentAndClass(classId, studentId).orElse(null);
        if (existingEnrollment != null)
            System.out.println(existingEnrollment.getEnrollmentStatus());

        // Reject if completed or attempted to register before
        if (existingEnrollment != null &&
                (existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.SUBMITTED
                        || existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.CONFIRMED
                        || existingEnrollment.getEnrollmentStatus() == EnrollmentEnum.COMPLETED
                )) {
            success = false;
        }

        // If no current enrollment
        else if (success = true) {
            // Create a new enrollment object
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourseClass(courseClass);
            enrollment.setEnrollmentStatus(EnrollmentEnum.SUBMITTED);
            System.out.print(enrollment.getEnrollmentId());

            enrollmentRepository.save(enrollment);


            // Send confirmation email with link
            String testRecepientEmail = "sa56team6@outlook.com";

            String confirmationLink = emailUtility.generateConfirmationLink(studentId, classId);
            emailService.sendConfirmationEmail(testRecepientEmail, confirmationLink, student.getFullName(), courseClass);
        }
        return ResponseEntity.ok(success);

    }

}