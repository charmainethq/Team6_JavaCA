package sg.edu.iss.team6.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    Enrollment findByEnrollmentId(long enrollmentId);
    void deleteByEnrollmentId(long enrollmentId);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByCourseClass(CourseClass courseClass);
    List<Enrollment> findByStudent(Student student);
    Optional<Enrollment> findByStudentAndCourseClass(Student student, CourseClass courseclass);

}
