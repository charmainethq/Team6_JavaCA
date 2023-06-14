package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.iss.team6.model.*;
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
    public void deleteByUsername(String username);
}
