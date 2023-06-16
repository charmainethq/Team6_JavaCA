package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.service.CourseService;

@Controller
@RequestMapping(value = "/admin/course")
public class AdminCourseController {
    @Autowired
    private CourseService cService;
    @GetMapping(value = "/list")
    public String getAllCourses(Model model){
        model.addAttribute("allCourses", cService.findAllCourses());
        return "course-list";
    }
    @GetMapping(value = "/{id}")
    public String getCourseById(Model model, @PathVariable long id){
        model.addAttribute("course", cService.findByCourseId(id));
        return "course-detail";
    }
    @GetMapping(value = "/create")
    public String createCoursePage(Model model){
        model.addAttribute("course", new Course());
        return "course-create";
    }

    @PostMapping(value = "/create")
    public String createCourse(@ModelAttribute("course") Course course){
        // Save the course object to the database
        cService.create(course);
        return "redirect:/admin/course/list";
    }
    @GetMapping("/update/{id}")
    public String updateStudentPage(@PathVariable("id") long id, Model model){
        Course course = cService.findByCourseId(id);
        model.addAttribute("course", course);
        return "course-update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute("course") Course course){
        cService.update(course);
        return "redirect:/admin/course/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable("id") long id) {
        cService.delete(id);
        return "redirect:/admin/course/list";
    }
}
