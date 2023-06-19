package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EnrollmentService {


    Optional<Enrollment> findByStudentAndClass(long classId, long studentId);
    void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum status);


	Enrollment findById(long id);

	Enrollment update(Enrollment currentEnrollment);
    
	ArrayList<Enrollment> findByClassId(long classId);

}
