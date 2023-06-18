package sg.edu.iss.team6.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.CourseClass;
import java.util.ArrayList;
import java.util.List;

public interface CourseClassService {

    List<CourseClass> findByCourseId(Long courseId);
    Page<CourseClass> findByCourseId(Long courseId, Pageable pageable);
    CourseClass findByClassId(Long classId);


}
