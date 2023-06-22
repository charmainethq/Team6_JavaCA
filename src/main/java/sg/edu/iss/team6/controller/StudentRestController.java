package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.service.*;
import sg.edu.iss.team6.utility.EmailUtility;

import java.util.List;
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

    @GetMapping(value = "/registerCourses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getUnenrolledAndFailedCourses() {
        try {
            //TODO: remove and use session instead
            Long studentId = testId;

            Student student = studentService.findByStudentId(studentId);
            List<Course> allCourses = courseService.getAllCourses();

            if (allCourses == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            List<Course> unenrolledAndFailedCourses = allCourses.stream()
                    .filter(course -> {
                        // filter for enrollment
                        boolean hasEnrollment = student.getStudentEnrollments().stream()
                                .anyMatch(enrollment ->
                                        enrollment.getCourseClass().getCourse().getCourseId() == course.getCourseId());

                        // Check if the enrollment status is withdrawal or failure
                        boolean isWithdrawnOrFailed = student.getStudentEnrollments().stream().anyMatch(enrollment ->
                                enrollment.getCourseClass().getCourse().getCourseId() == course.getCourseId() &&
                                        (enrollment.getEnrollmentStatus() == EnrollmentEnum.WITHDRAWN ||
                                                enrollment.getEnrollmentStatus() == EnrollmentEnum.FAILED ||
                                                enrollment.getEnrollmentStatus() == EnrollmentEnum.REMOVED));

                        // only include if hasEnrollment is false AND fail/withdraw is true.
                        return !hasEnrollment || isWithdrawnOrFailed;
                    })
                    .collect(Collectors.toList());
            return new ResponseEntity<>(unenrolledAndFailedCourses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/fetchClasses/{courseId}")
    public ResponseEntity<Page<CourseClass>> getClassesByCourseId(
            @PathVariable("courseId") Long courseId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<CourseClass> classes = classService.findByCourseId(courseId, pageable);
            //List<CourseClass> classes = classService.findByCourseId(courseId);

            if (classes.getContent().size() == 0)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return ResponseEntity.ok(classes);

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


    @GetMapping(value = "/register/{classId}/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
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