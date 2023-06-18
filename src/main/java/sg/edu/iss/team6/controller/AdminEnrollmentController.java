package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.StudentService;

@Controller
@RequestMapping("/admin/enrollment")
public class AdminEnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    StudentService studentService;
    
    @Autowired
    CourseClassService ccService;

    @GetMapping("/list")
    public String getAllEnrollment(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAllEnrollments());
        return "enrollment-list";
    }

    @GetMapping("/{enrollmentId}/status")
    public String showUpdateForm(@PathVariable("enrollmentId") Long enrollmentId, Model model) {
        Enrollment enroll = enrollmentService.findByEnrollmentId(enrollmentId);
        model.addAttribute("enrollments", enroll);
        return "enrollment-update";
    }
    
    @PostMapping("/{enrollmentId}/status")
    public ModelAndView updateEnrollmentStatus(
            @PathVariable("enrollmentId") long enrollmentId,
            @RequestParam("status") String newStatus) {
        enrollmentService.updateEnrollmentStatus(enrollmentId, EnrollmentEnum.valueOf(newStatus));

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/enrollment/list");
        modelAndView.addObject("message", "Enrollment status updated successfully.");
        return modelAndView;
    }

    // @GetMapping("/student/{studentId}/{classId}/remove")
    // public String showRemoveStudentFromCoursePage(
    //         @PathVariable("studentId") long studentId,
    //         @PathVariable("classId") long classId,
    //         Model model) {

    //     Student student = studentService.findByStudentId(studentId);
    //     CourseClass cc = ccService.findByClassId(classId);

    //     model.addAttribute("studentId", studentId);
    //     model.addAttribute("classId", cc);
    //     model.addAttribute("student", student);

    //     return "remove-student-from-course";
    // }
    
    // @DeleteMapping("/student/{studentId}/{classId}/remove")
    // public ModelAndView removeStudentFromCourse(
    //         @PathVariable("studentId") long studentId,
    //         @PathVariable("classId") long classId) {
    //     enrollmentService.removeStudentFromClass(studentId, classId);

    //     ModelAndView modelAndView = new ModelAndView("remove-student-from-course");
    //     modelAndView.addObject("message", "Student removed from the course successfully.");
    //     return modelAndView;
    // }

    @GetMapping("/student/{enrollmentId}/remove")
    public String removeStudentByClassId(@PathVariable("enrollmentId") long enrollmentId){
        enrollmentService.delete(enrollmentId);
        return "remove-student-from-course";
    }


}