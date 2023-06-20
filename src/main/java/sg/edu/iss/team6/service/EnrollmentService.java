package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.*;

import java.util.ArrayList;
import sg.edu.iss.team6.model.EnrollmentEnum;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {


    Optional<Enrollment> findByStudentAndClass(long classId, long studentId);
    void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum newStatus);

	Enrollment findById(long id);

    
	ArrayList<Enrollment> findByClassId(long classId);

    List<Enrollment> findAllEnrollments();
    List<Enrollment> findByCourseClass(CourseClass courseClass);
    List<Enrollment> findByStudent(Student student);
    Enrollment findByEnrollmentId(long id);
    Enrollment create(Enrollment enrollment);
    Enrollment update(Enrollment enrollment);
    void delete(long id);
    void deleteList(List<Enrollment> enrollments);



}
