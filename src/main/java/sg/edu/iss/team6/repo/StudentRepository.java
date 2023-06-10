package sg.edu.iss.team6.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.Student;

import java.util.ArrayList;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s from Student s")
    ArrayList<Student> findAllStudent();
}

