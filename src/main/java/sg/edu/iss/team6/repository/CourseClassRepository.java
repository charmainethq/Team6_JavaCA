package sg.edu.iss.team6.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.team6.model.*;
public interface CourseClassRepository extends JpaRepository<CourseClass, Long>{
	
	
	  @Query("SELECT c.lecturer.lecturerId FROM CourseClass c WHERE c.lecturer.lecturerId=:lecId")	  		
      public List<Long>findByLecturerId(@Param("lecId")long lecId);
}
