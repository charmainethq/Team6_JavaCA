package sg.edu.iss.team6.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team6.model.CourseClass;
public interface CourseClassRepository extends JpaRepository<CourseClass, Long>{
	
	@Query ("SELECT DISTINCT cc.course.courseId FROM CourseClass cc WHERE cc.lecturer.lecturerId = :lecturerId")
	public List<Long> findDistinctCourseId(@Param("lecturerId") long lecturerId);
	
	@Query ("SELECT cc FROM CourseClass cc WHERE cc.lecturer.lecturerId = :lecturerId")
	public ArrayList<CourseClass> findByLecturerId(@Param("lecturerId") long lecturerId);
	
}
