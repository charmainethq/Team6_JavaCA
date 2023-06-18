package sg.edu.iss.team6.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminEnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    StudentService studentService;
    
    @Autowired
    CourseService courseService;

    @GetMapping("/enrollment/list")
    public String getAllEnrollment(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAllEnrollments());
        return "enrollment-list";
    }

    @GetMapping("/enrollment/{enrollmentId}/status")
    public String showUpdateForm(@PathVariable("enrollmentId") Long enrollmentId, Model model) {
        Enrollment enroll = enrollmentService.findByEnrollmentId(enrollmentId);
        model.addAttribute("enrollments", enroll);
        return "enrollment-update";
    }

    @PostMapping("/enrollment/{enrollmentId}/status")
    public ResponseEntity<Map<String, String>> updateEnrollmentStatus(
            @PathVariable("enrollmentId") long enrollmentId,
            @RequestParam("status") String newStatus) {
        enrollmentService.updateEnrollmentStatus(enrollmentId, EnrollmentEnum.valueOf(newStatus));
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Enrollment status updated successfully.");
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}/remove")
    public ResponseEntity<String> removeStudentFromCourse(
            @PathVariable("studentId") long studentId,
            @PathVariable("courseId") long courseId) {
        enrollmentService.removeStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok("Student removed from the course successfully.");
    }

    @GetMapping("/student/{studentId}/course/{courseId}/remove")
    public String showRemoveStudentFromCoursePage(
            @PathVariable("studentId") long studentId,
            @PathVariable("courseId") long courseId,
            Model model) {

        // Populate the model with necessary data
        Student student = studentService.findByStudentId(studentId);
        Course course = courseService.findByCourseId(courseId);

        model.addAttribute("studentId", studentId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("student", student);
        model.addAttribute("course", course);

        return "remove-student-from-course";
    }
}