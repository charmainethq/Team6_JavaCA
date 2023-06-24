package sg.edu.iss.team6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.AdminRepository;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public List<Admin> findAll(){
        return adminRepo.findAll();
    }

    @Override
    public Admin findById(Long id){
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public Admin create(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public Admin update(Admin admin){
        return adminRepo.save(admin);
    }

    @Override
    public Long delete(Long id){
        adminRepo.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public Admin findByUser(User u){
        return adminRepo.findByUser(u);
    }

    @Override
    @Transactional
    public Admin findByUserUsername(String username){
        Admin admin = adminRepo.findByUserUsername(username);
        return admin;
    }

}
