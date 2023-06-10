package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.service.StudentService;

import java.util.*;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/students")
    public @ResponseBody List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

}
