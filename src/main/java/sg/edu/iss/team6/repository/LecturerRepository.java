package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;


public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
    
    Lecturer findByUser(User u);
}
