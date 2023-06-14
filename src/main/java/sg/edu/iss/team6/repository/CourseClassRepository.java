package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.*;

import java.util.List;

public interface CourseClassRepository extends JpaRepository<CourseClass, Integer>{
    //@Query("SELECT cc FROM CourseClass cc WHERE cc.course.courseId = :courseId")
    List<CourseClass> findAllByCourseCourseId(Long courseId);
    CourseClass findByClassId(Long classId);



}
