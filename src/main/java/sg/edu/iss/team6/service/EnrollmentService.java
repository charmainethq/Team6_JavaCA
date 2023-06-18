package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;

import java.util.ArrayList;
import java.util.Optional;

public interface EnrollmentService {
     Optional<Enrollment> findByStudentAndClass(long classId, long studentId);
     void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum status);
}
