package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();
    Course findByCourseId(long id);
    Course create(Course course);
    Course update(Course course);
    void delete(long id);
}
