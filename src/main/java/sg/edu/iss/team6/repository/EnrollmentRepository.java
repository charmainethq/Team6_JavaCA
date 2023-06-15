package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

    //List<Enrollment> findAllByCourse
}
