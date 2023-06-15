package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.team6.model.*;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(User u);
    Student findByStudentId(Long studentId);

}

