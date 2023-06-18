package sg.edu.iss.team6.repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.team6.model.*;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	@Query("SELECT e FROM Enrollment e WHERE e.courseClass.classId = :classId")
	public ArrayList<Enrollment> findEnrollmentByCourseID(@Param("classId") long classId);
}
