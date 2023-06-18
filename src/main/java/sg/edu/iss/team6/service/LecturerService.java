package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;

import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Student;

import java.util.ArrayList;
import java.util.List;

public interface LecturerService {

	List<Lecturer> findAll();
	
	Lecturer findById(long id);
	
	Lecturer update(Lecturer lecturer);
	
//	public List<Course> viewCourseEnrolment();
//	public void gradeCourse();
//	public List<Student> viewStudentsPerformance();
//	public String logIn();
}
