package sg.edu.iss.team6.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.team6.model.*;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public List<Admin>findByFirstName(String firstName);
    public List<Admin>findBylastName(String lastName);
    public List<Admin>findByEmail(String email);
    public List<Admin>findByContactNo(String contactNo);
    public List<Admin>findByAddress(String address);


    public List<Admin>findByUser_Username(String username);
}
