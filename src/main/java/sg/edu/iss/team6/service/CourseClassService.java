package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.CourseClass;

import java.util.ArrayList;
import java.util.List;
public interface CourseClassService {
	
	CourseClass findById(long Id);
	
	List<Long> findDistinctCourseId(long lecturerId);
	
	ArrayList<CourseClass> findByLecturerId(long lecturerId);
}
