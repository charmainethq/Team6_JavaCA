package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.iss.team6.model.*;

import java.util.List;

@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long>{
    List<CourseClass> findByCourse(Course course);
    CourseClass findByClassId(long id);
    void deleteByClassId(long id);
}
