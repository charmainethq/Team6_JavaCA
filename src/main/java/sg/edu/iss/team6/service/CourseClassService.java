package sg.edu.iss.team6.service;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.CourseClass;
import java.util.ArrayList;
import java.util.List;

public interface CourseClassService {

    List<CourseClass> findByCourseId(Long courseId);
    CourseClass findByClassId(Long classId);
}
