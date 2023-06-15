package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.team6.service.CourseService;

import java.security.PrivateKey;

@Controller
@RequestMapping(value = "/admin/course")
public class AdminCourseController {
    @Autowired
    private CourseService cService;
}
