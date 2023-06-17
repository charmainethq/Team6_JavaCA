package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.LecturerRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class LecturerServiceImpl implements LecturerService {
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
    public Lecturer create(Lecturer l) {
        return lrepo.save(l);
    }

    @Override
    public Lecturer update(Lecturer l){
        return lrepo.save(l);
    }

    @Override
    public int delete(int id){
        lrepo.deleteById(id);
        return id;
    }
}
