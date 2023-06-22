package sg.edu.iss.team6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.LecturerRepository;

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
    public Lecturer findById(Long id){
        return lrepo.findById(id).orElse(null);
    }

    // @Override
    // public Lecturer findById(long id) {
    //     return null;
    // }

    @Override

    public Lecturer create(Lecturer lect) {
        return lrepo.save(lect);
    }

    @Override
    public Lecturer update(Lecturer lect){
        return lrepo.save(lect);

    }


    @Override
    public long delete(long id){
        lrepo.deleteById(id);
        return id;
    }

    @Override
    public Long delete(Long id) {
         lrepo.deleteById(id);
        return id;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Lecturer> findByUser_Username(String username){
    	return lrepo.findByUser_Username(username);
    }

}
