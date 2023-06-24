package sg.edu.iss.team6.repository;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.team6.model.*;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


    Admin findByUserUsername(String username);

    Admin findByUser(User u);
}
