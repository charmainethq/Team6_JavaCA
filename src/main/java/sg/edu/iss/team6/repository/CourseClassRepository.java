package sg.edu.iss.team6.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.*;

import java.util.List;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team6.model.*;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface CourseClassRepository extends JpaRepository<CourseClass, Long>{
    List<CourseClass> findByCourse(Course course);

    //@Query("SELECT cc FROM CourseClass cc WHERE cc.course.courseId = :courseId")
    Page<CourseClass> findAllByCourseCourseId(Long courseId, Pageable pageable);
    List<CourseClass> findAllByCourseCourseId(Long courseId);
    CourseClass findByClassId(Long classId);

	@Query ("SELECT DISTINCT cc.course.courseId FROM CourseClass cc WHERE cc.lecturer.lecturerId = :lecturerId")
	public List<Long> findDistinctCourseId(@Param("lecturerId") long lecturerId);

	@Query ("SELECT cc FROM CourseClass cc WHERE cc.lecturer.lecturerId = :lecturerId")
	public ArrayList<CourseClass> findByLecturerId(@Param("lecturerId") long lecturerId);

	@Query ("SELECT cc FROM CourseClass cc WHERE cc.course.courseId = :courseId")
	public ArrayList<CourseClass> findByCourseId(@Param("courseId") long courseId);

    CourseClass findByClassId(long id);
    void deleteByClassId(long id);
}
