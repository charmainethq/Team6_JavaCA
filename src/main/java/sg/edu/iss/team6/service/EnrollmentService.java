package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.model.Student;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> findAllEnrollments();
    List<Enrollment> findByCourseClass(CourseClass courseClass);
    List<Enrollment> findByStudent(Student student);
    Enrollment findByEnrollmentId(long id);
    Enrollment create(Enrollment enrollment);
    Enrollment update(Enrollment enrollment);
    void delete(long id);
    void deleteList(List<Enrollment> enrollments);

    void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum newStatus);
    void removeStudentFromCourse(long studentId, long courseId);

}
