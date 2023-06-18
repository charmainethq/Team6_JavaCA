package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Student;

import java.util.ArrayList;
import java.util.List;


public interface EnrollmentService {

	ArrayList<Enrollment> findByClassId(long classId);
	ArrayList<Enrollment> findEnrollmentByStudentID(long studentId);	
	ArrayList<Enrollment> findStudentByClassId(long classId);

}
