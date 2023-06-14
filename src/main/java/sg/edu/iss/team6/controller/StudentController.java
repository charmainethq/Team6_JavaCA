package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.StudentService;

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
    CourseService courseService;


    @RequestMapping(value = "/all")
    public @ResponseBody List<String> findAllStudents(){

        List<Student> students = studentService.findAllStudents();
        List<String> studentNames = new ArrayList<>();
        for (Student s: students
             ) {studentNames.add(s.getFirstName());
        }

        return studentNames;
    }






    @GetMapping(value = "{courseId}/viewClasses")
    public String getClassesByCourseId(@PathVariable("courseId") Long courseId, Model model) {
        List<CourseClass> classes = classService.findByCourseId(courseId);
        model.addAttribute("classes", classes);

        //TODO: remove and use session instead
        Student s =  studentService.findByStudentId(1L);
        model.addAttribute("student",s);

        return "student-classList";
    }

    @PostMapping("/register")
    public String registerClass(@RequestParam("studentId") Long studentId, @RequestParam("classId") Long classId) {

        // Retrieve the student and class based on the provided IDs
        Student student = studentService.findByStudentId(studentId);
        CourseClass courseClass = classService.findByClassId(classId);

        //TODO: exceptions

        // Create a new enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourseClass(courseClass);
        enrollment.setEnrollmentStatus(EnrollmentEnum.SUBMITTED);
        System.out.print(enrollment.getEnrollmentId());

        // Save the enrollment object to the database
        enrollmentRepository.save(enrollment);

        return "redirect:/student/registerSuccess";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess(){
        return "student-registerSuccess";
    }



}
