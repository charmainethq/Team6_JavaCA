package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();
    Course findByCourseId(long id);
    Course create(Course course);
    Course update(Course course);
    void delete(long id);
  
    List<Course> getAllCourses();
    Course findCourseByCourseId(Long courseId);

    Course findById(long id);
}
