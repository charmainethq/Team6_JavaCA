package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.LecturerRepository;


@Service
public class LecturerServiceImpl implements LecturerService{

    @Autowired
    LecturerRepository lrepo;

    @Override
    @Transactional
    public Lecturer findByUser(User u){
        return lrepo.findByUser(u);
    }
}
