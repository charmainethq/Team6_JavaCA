package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.StudentRepository;
import sg.edu.iss.team6.repository.UserRepository;
import sg.edu.iss.team6.service.StudentService;
import sg.edu.iss.team6.service.UserService;
import sg.edu.iss.team6.service.UserServiceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin/student")
public class AdminStudentController {
    @Autowired
    private StudentService sService;
    @Autowired
    private UserService uService;

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
        return "student-create";
    }

    @PostMapping(value = "/create")
    public String createStudent(@ModelAttribute("student") Student student){
        // Remember to save user object
        User user = student.getUser();
        uService.create(user);
        // Save the student object to the database
        sService.create(student);
        return "redirect:/admin/student/list";
    }
    @GetMapping("/update/{id}")
    public String updateStudentPage(@PathVariable("id") long id, Model model){
        Student student = sService.findByStudentId(id);
        model.addAttribute("student", student);
        return "student-update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute("student") Student student){
        User user = student.getUser();
        uService.update(user);
        sService.update(student);
        return "redirect:/admin/student/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") long id) {
        sService.delete(id);
        return "redirect:/admin/student/list";
    }
}
