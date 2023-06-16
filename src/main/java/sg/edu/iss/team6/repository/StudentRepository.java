package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentId(Long studentId);
    Student findByUserUsername(User u);
    void deleteByStudentId(long id);

}

