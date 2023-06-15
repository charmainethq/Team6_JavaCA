package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repository.StudentRepository;
import sg.edu.iss.team6.service.StudentService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/admin/student")
public class AdminStudentController {
    @Autowired
    private StudentService sService;

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
    public String createStudent(Model model, Student student){
        model.addAttribute("student", student);
        return "student-create";
    }

    @PostMapping(value = "/create")
    public String saveStudent(Model model, @ModelAttribute("student") Student student){
        Student newStudent = new Student();
        newStudent.setStudentId(student.getStudentId());
        return "redirect:/admin/student/list";
    }
}
