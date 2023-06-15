package sg.edu.iss.team6.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.repository.AdminRepository;
import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository arepo;

    @Override
    @Transactional
    public Admin findByUsername(User u){
        return arepo.findByUserUsername(u);
    }
}