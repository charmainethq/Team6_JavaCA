package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.StudentRepository;
import java.util.ArrayList;


@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentRepository srepo;

    @Override
    public ArrayList<Student> findAllStudents() {
        return (ArrayList<Student>) srepo.findAll();
    }

    @Override
    @Transactional
    public Student findByStudentId(Long studentId) {
        return srepo.findByStudentId(studentId);
    }

    @Override
    public Student create(Student student) {
        return srepo.save(student);
    }

    @Override
    public Student update(Student student) {
        return srepo.save(student);
    }

    @Override
    @Transactional
    public void delete(long id) {
        srepo.deleteByStudentId(id);
    }

    @Override
    @Transactional
    public Student findByUser(User u) {
        return srepo.findByUserUsername(u);
    }

    @Override
    @Transactional
    public Student findByUser(User u) {
        return srepo.findByUser(u);


}
