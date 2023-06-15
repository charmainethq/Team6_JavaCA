package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.User;

public interface UserService {

    User findByUsernameAndPassword(String username,String password);
}