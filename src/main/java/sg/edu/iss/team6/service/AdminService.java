package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> findAll();
	
	Admin findById(int id);
 
    Admin create(Admin admin);
 
    Admin update(Admin admin);

    int delete(int id);
}
