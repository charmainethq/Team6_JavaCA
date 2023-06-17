package sg.edu.iss.team6.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.iss.team6.model.*;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
    Lecturer findByUser(User u);

    @Query("SELECT l from Lecturer l where l.user.username = :username")
    public List<Lecturer> findByUser_Username(@Param("username")String username);
}
