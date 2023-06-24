package sg.edu.iss.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.service.CourseClassService;
import sg.edu.iss.team6.service.CourseService;
import sg.edu.iss.team6.service.EnrollmentService;
import sg.edu.iss.team6.service.LecturerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/course")
public class AdminCourseController {
    @Autowired
    private CourseService cService;
    @Autowired
    private CourseClassService ccService;
    @Autowired
    private LecturerService lService;
    @Autowired
    private EnrollmentService eService;

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
    public String createCourse(@ModelAttribute("course") @Valid Course course,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Return the same view if there are validation errors
            return "course-create";
        }
        // Save the course object to the database
        cService.create(course);
        return "redirect:/admin/course/list";
    }
    @GetMapping(value = "/update/{id}")
    public String updateCoursePage(@PathVariable("id") long id, Model model){
        Course course = cService.findByCourseId(id);
        model.addAttribute("course", course);
        return "course-update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateCourse(@PathVariable("id") long id,
                               @ModelAttribute("course") @Valid Course course,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "course-update";
        }
        cService.update(course);
        return "redirect:/admin/course/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable("id") long id) {
        List<CourseClass> classes = ccService.findByCourseId(id);
        ccService.deleteList(classes);
        cService.delete(id);
        return "redirect:/admin/course/list";
    }

    @GetMapping(value = "/class/{id}")
    public String getClassesByCourseId(Model model, @PathVariable long id){
        Course course = cService.findByCourseId(id);
        model.addAttribute("course", course);
        List<CourseClass> courseClasses = ccService.findByCourse(course);
        model.addAttribute("courseClasses", courseClasses);
        return "course-class";
    }
    @GetMapping(value = "/class/create/{id}")
    public String createClassPage(@PathVariable("id") long id, Model model){
        Course course = cService.findByCourseId(id);
        List<Lecturer> lecturers = lService.findAll();
        model.addAttribute("course", course);
        model.addAttribute("courseClass", new CourseClass());
        model.addAttribute("lecturers", lecturers);
        return "course-class-create";
    }
    @PostMapping(value = "/class/create/{id}")
    public String createClass(@ModelAttribute("courseClass") @Valid CourseClass cc,
                              BindingResult bindingResult,
                              @PathVariable("id") @RequestParam("course.courseId") long courseId,
                              @RequestParam("lecturer") Lecturer lecturer,
                              Model model){
        if (bindingResult.hasErrors()) {
            Course course = cService.findByCourseId(courseId);
            List<Lecturer> lecturers = lService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("lecturers", lecturers);
            return "course-class-create";
        }
        Course course = cService.findByCourseId(courseId);
        cc.setCourse(course);
        cc.setLecturer(lecturer);
        ccService.create(cc);
        return "redirect:/admin/course/class/" + cc.getCourse().getCourseId();
    }
    @GetMapping("/class/update/{id}")
    public String updateClassPage(@PathVariable("id") long id, Model model){
        CourseClass cc = ccService.findByClassId(id);
        List<Lecturer> lecturers = lService.findAll();
        model.addAttribute("courseClass", cc);
        model.addAttribute("lecturers", lecturers);
        return "course-class-update";
    }
    @PostMapping(value = "/class/update/{id}")
    public String updateClass(@ModelAttribute("courseClass") @Valid CourseClass cc,
                              BindingResult bindingResult,
                              @PathVariable("id") long id,
                              @RequestParam("lecturer") Lecturer lecturer,
                              Model model){
        if (bindingResult.hasErrors()) {
            List<Lecturer> lecturers = lService.findAll();
            model.addAttribute("lecturers", lecturers);
            return "course-class-update";
        }
        ccService.update(cc);
        return "redirect:/admin/course/class/" + cc.getCourse().getCourseId();
    }

    @GetMapping("/class/delete/{id}")
    public String deleteClassById(@PathVariable("id") long id) {
        CourseClass cc = ccService.findByClassId(id);
        List<Enrollment> enrollments = eService.findByCourseClass(cc);
        eService.deleteList(enrollments);
        ccService.delete(id);
        return "redirect:/admin/course/class/" + cc.getCourse().getCourseId();
    }

    @GetMapping("/class/enrollments/{id}")
    public String getEnrollmentsByClassId(Model model, @PathVariable long id){
        CourseClass courseClass = ccService.findByClassId(id);
        List<Enrollment> enrollments = eService.findByCourseClass(courseClass);
        model.addAttribute("enrollments", enrollments);
        return "course-class-enrollments";
    }

}
