package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.iss.team6.model.*;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseId(long courseId);
    void deleteByCourseId(long courseId);

    Course findCourseByCourseId(Long courseId);


}
