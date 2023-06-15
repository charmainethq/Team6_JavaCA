package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.iss.team6.model.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

    Enrollment findByCourseClassClassIdAndStudentStudentId(long classId, long studentId);
    @Modifying
    @Query("UPDATE Enrollment e SET e.enrollmentStatus = :status WHERE e.enrollmentId = :enrollmentId")
    int updateEnrollmentStatus(@Param("enrollmentId") long enrollmentId, @Param("status") EnrollmentEnum status);
}
