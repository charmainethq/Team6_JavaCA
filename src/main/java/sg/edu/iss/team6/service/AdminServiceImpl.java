package sg.edu.iss.team6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.repository.AdminRepository;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Override
    public List<Admin> findAll(){
        return adminRepo.findAll();
    }

    @Override
    public Admin findById(int id){
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
    public int delete(int id){
        adminRepo.deleteById(id);
        return id;
    }

    

}
