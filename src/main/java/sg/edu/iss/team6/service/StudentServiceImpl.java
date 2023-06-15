package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.StudentRepository;
import java.util.ArrayList;


@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentRepository srepo;

    @Override
    @Transactional
    public ArrayList<Student> findAllStudents() {
        return (ArrayList<Student>) srepo.findAll();
    }

    @Override
    @Transactional
    public Student findByuser(User u) {
        return srepo.findByuser(u);
    }
}
