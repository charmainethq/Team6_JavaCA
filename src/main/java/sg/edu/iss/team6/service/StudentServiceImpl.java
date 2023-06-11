package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repository.StudentRepository;
import java.util.ArrayList;


@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentRepository srepo;

    @Override
    @Transactional
    public ArrayList<Student> findAllStudents() {
        System.out.println("Inside findAllStudents() method");
        ArrayList<Student> students = (ArrayList<Student>) srepo.findAllStudents();
        System.out.println("Number of students found: " + students.size());
        return students;
    }
}
