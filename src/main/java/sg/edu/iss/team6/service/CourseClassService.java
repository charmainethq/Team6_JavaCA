package sg.edu.iss.team6.service;

import sg.edu.iss.team6.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.CourseClass;
import java.util.ArrayList;
import java.util.List;

public interface CourseClassService {

    List<CourseClass> findByCourse(Course course);

    List<CourseClass> findByCourseId(Long courseId);
    Page<CourseClass> findByCourseId(Long courseId, Pageable pageable);
    CourseClass findByClassId(Long classId);
    
	CourseClass findById(long Id);
	
	List<Long> findDistinctCourseId(long lecturerId);
	
	ArrayList<CourseClass> findByLecturerId(long lecturerId);
}
