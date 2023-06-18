package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


public interface CourseClassService {

//	List<CourseClass> findCourseClassByLecturerId(long lecId);
	
	List<Long>findByLecturerId(long lecId);
}
