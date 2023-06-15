package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
    @Resource
    UserRepository urepo;

    @Override
    @Transactional
    public User findByUsernameAndPassword(String username,String password) {

        return urepo.findByUsernameAndPassword(username,password);
    }
}
