package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;

import java.util.List;


public interface CourseService {
    List<Course> getAllCourses();
    Course findCourseByCourseId(Long courseId);






}
