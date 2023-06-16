package sg.edu.iss.team6.service;
import java.util.List;

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
    
    @Override
    public List<Lecturer> findAll(){
        return lrepo.findAll();
    }

    @Override
    public Lecturer findById(int id){
        return lrepo.findById(id).orElse(null);
    }

    @Override
    public Lecturer create(Lecturer admin) {
        return lrepo.save(admin);
    }

    @Override
    public Lecturer update(Lecturer admin){
        return lrepo.save(admin);
    }

    @Override
    public int delete(int id){
        lrepo.deleteById(id);
        return id;
    }
}
