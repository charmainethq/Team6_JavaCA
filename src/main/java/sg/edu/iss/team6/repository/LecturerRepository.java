package sg.edu.iss.team6.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;
public interface LecturerRepository extends JpaRepository<Lecturer, Long>{
	
}
