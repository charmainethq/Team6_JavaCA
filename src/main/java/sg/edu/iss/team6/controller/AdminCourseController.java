package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.LecturerService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/course")
public class AdminCourseController {
    @Autowired
    private CourseService cService;
    @Autowired
    private CourseClassService ccService;

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
    public String updateCoursePage(@PathVariable("id") long id, Model model){
        Course course = cService.findByCourseId(id);
        model.addAttribute("course", course);
        return "course-update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateCourse(@PathVariable("id") long id, @ModelAttribute("course") Course course){
        cService.update(course);
        return "redirect:/admin/course/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable("id") long id) {
        cService.delete(id);
        return "redirect:/admin/course/list";
    }

    @GetMapping(value = "/class/{id}")
    public String getClassesById(Model model, @PathVariable long id){
        Course course = cService.findByCourseId(id);
        model.addAttribute("course", course);
        List<CourseClass> courseClasses = ccService.findByCourse(course);
        model.addAttribute("courseClasses", courseClasses);
        return "course-class";
    }
    @GetMapping(value = "/class/create")
    public String createClassPage(Model model){
        model.addAttribute("class", new CourseClass());
        return "course-class-create";
    }
    @PostMapping(value = "/class/create")
    public String createClass(@ModelAttribute("class") CourseClass cc){
        ccService.create(cc);
        return "course-class";
    }
    @GetMapping("/class/update/{id}")
    public String updateClassPage(@PathVariable("id") long id, Model model){
        CourseClass cc = ccService.findByClassId(id);
        model.addAttribute("class", cc);
        return "course-class-update";
    }
    @PostMapping(value = "/class/update/{id}")
    public String updateClass(@PathVariable("id") long id, @ModelAttribute("class") CourseClass cc){
        ccService.update(cc);
        return "course-class";
    }

    @GetMapping("/class/delete/{id}")
    public String deleteClassById(@PathVariable("id") long id) {
        ccService.delete(id);
        return "course-class";
    }
}
