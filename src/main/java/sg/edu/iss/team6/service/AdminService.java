package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Admin;


import javax.transaction.Transactional;

import sg.edu.iss.team6.model.User;


import java.util.List;

public interface AdminService {
    List<Admin> findAll();
	
	Admin findById(Long id);
 
    Admin create(Admin admin);
 
    Admin update(Admin admin);

    @Transactional
    Long delete(Long id);

    Admin findByUser(User u);
}
