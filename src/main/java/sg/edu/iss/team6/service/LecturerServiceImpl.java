package sg.edu.iss.team6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.LecturerRepository;


@Service
public class LecturerServiceImpl implements LecturerService{

    @Autowired
    LecturerRepository lrepo;

    @Override
    @Transactional
    public Lecturer findByUsername(User u){
        return lrepo.findByUsername(u);
    }

}