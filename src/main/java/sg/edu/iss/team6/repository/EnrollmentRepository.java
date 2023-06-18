package sg.edu.iss.team6.repository;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team6.model.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
	
	 @Query("SELECT e FROM Enrollment e WHERE e.courseClass.classId=:classid")	  		
     public ArrayList<Enrollment> findByClassId(@Param("classid")long classid);
}
