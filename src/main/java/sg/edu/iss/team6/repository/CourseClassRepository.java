package sg.edu.iss.team6.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.*;

import java.util.List;

public interface CourseClassRepository extends JpaRepository<CourseClass, Integer>{
    //@Query("SELECT cc FROM CourseClass cc WHERE cc.course.courseId = :courseId")
    Page<CourseClass> findAllByCourseCourseId(Long courseId, Pageable pageable);
    List<CourseClass> findAllByCourseCourseId(Long courseId);
    CourseClass findByClassId(Long classId);



}
