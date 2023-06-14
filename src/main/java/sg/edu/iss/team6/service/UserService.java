package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.User;
import java.util.ArrayList;
import java.util.List;
public interface UserService {

    List<User> findAll();
	
	User findByUsername(String username);
 
    User create(User user);
 
    User update(User user);

    void delete(String username);
}
