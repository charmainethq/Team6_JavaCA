package sg.edu.iss.team6.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import sg.edu.iss.team6.model.*;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long>{
    
    Lecturer findByUser(User u);

    @Query("SELECT lecturer.lecturerId FROM Lecturer lecturer WHERE lecturer.user.username = :username")
    Long findLecturerIdByUserUsername(@Param("username") String username);

    Lecturer findByUserUsername(String username);

}
