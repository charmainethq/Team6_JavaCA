package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;

import java.util.ArrayList;
import java.util.List;

public interface EnrollmentService {
    List<Enrollment> findAllEnrollments();
    Enrollment findByEnrollmentId(long id);
    Enrollment create(Enrollment enrollment);
    Enrollment update(Enrollment enrollment);
    void delete(long id);

    Optional<Enrollment> findByStudentAndClass(long classId, long studentId);
    void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum status);

}
