package sg.edu.iss.team6.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repo.StudentRepository;
import sg.edu.iss.team6.service.StudentService;

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
}
