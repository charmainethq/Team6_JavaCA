package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;

import java.util.List;

public interface CourseClassRepository extends JpaRepository<CourseClass, Integer>{
    List<CourseClass> findByCourse(Course course);
}
