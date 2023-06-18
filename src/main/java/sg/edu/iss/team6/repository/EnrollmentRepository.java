package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import sg.edu.iss.team6.model.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

    //List<Enrollment> findAllByCourse
    List<Enrollment> findAll();
    
}
