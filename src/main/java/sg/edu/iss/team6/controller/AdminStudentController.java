package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.StudentService;
import sg.edu.iss.team6.service.UserService;
import sg.edu.iss.team6.validator.UserValidator;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/admin/student")
public class AdminStudentController {
    @Autowired
    StudentService sService;
    @Autowired
    UserService uService;
    @Autowired
    EnrollmentService eService;

    @GetMapping(value = "/list")
    public String getAllStudents(Model model){
        model.addAttribute("allStudents", sService.findAllStudents());
        return "student-list";
    }
    @GetMapping(value = "/{id}")
    public String getStudentById(Model model, @PathVariable long id){
        model.addAttribute("student", sService.findByStudentId(id));
        return "student-detail";
    }

    @GetMapping(value = "/create")
    public String createStudentPage(Model model){
        model.addAttribute("student", new Student());
        List<User> stuUsers = uService.findAll();
        model.addAttribute("stuUsers", stuUsers);
        return "student-create";
    }

    @PostMapping(value = "/create")
    public String createStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                @RequestParam("username") String username,
                                Model model){
        User user = uService.findByUsername(username);
        Student existingStudent = sService.findByUser(user);
        if (existingStudent != null) {
            List<User> stuUsers = uService.findAll();
            model.addAttribute("stuUsers", stuUsers);
            bindingResult.rejectValue("user.username", "error.user.username.alreadyExists",
                    "A student has been created under this username.");
            return "student-create";
        }
        else {
            student.setUser(user);
            // Save the student object to the database
            sService.create(student);
            return "redirect:/admin/student/list";
        }
    }

    @GetMapping("/update/{id}")
    public String updateStudentPage(@PathVariable("id") long id, Model model){
        Student student = sService.findByStudentId(id);
        model.addAttribute("student", student);
        return "student-update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable("id") long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Return the same view if there are validation errors
            return "student-update";
        }
        sService.update(student);
        return "redirect:/admin/student/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") long id) {
        Student student = sService.findByStudentId(id);
        List<Enrollment> enrollments = eService.findByStudent(student);
        eService.deleteList(enrollments);
        sService.delete(id);
        return "redirect:/admin/student/list";
    }
}
