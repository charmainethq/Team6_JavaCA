package sg.edu.iss.team6.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.UserRepository;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public User update(User user){
        return userRepo.save(user);
    }

    @Override
    public void delete(String username){
        userRepo.deleteByUsername(username);
    }

    @Override
    @Transactional
    public User findByUsernameAndPassword(String username,String password) {

        return userRepo.findByUsernameAndPassword(username,password);
    }
}
