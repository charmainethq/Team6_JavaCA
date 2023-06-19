package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseId(long courseId);
    void deleteByCourseId(long courseId);

    Course findCourseByCourseId(Long courseId);


}
