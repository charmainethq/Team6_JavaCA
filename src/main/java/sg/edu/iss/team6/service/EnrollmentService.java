package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Enrollment;
import java.util.ArrayList;
import java.util.List;
public interface EnrollmentService {
	Enrollment findById(long id);

	public Enrollment update(Enrollment currentEnrollment);
	
	ArrayList<Enrollment> findByClassId(long classId);
}
