package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.StudentService;

import java.util.*;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseClassService classService;

    @RequestMapping(value = "/all")
    public @ResponseBody List<String> findAllStudents(){

        List<Student> students = studentService.findAllStudents();
        List<String> studentNames = new ArrayList<>();
        for (Student s: students
             ) {studentNames.add(s.getFirstName());

        }

        return studentNames;
    }

    @RequestMapping(value = "/classes")
    public @ResponseBody List<Long> getClassesByCourseId(){
        Long courseId = 2L;
        List<CourseClass> classes = classService.findByCourseId(courseId);
        List<Long> classNames = new ArrayList<>();
        for (CourseClass c: classes) {classNames.add(c.getClassId());
        }
        return classNames;
    }


}
