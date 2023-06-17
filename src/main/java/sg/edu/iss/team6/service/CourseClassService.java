package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import java.util.ArrayList;
import java.util.List;

public interface CourseClassService {
    List<CourseClass> findByCourse(Course course);
    CourseClass findByClassId(long id);
    CourseClass create(CourseClass c);

    CourseClass update(CourseClass c);
}
