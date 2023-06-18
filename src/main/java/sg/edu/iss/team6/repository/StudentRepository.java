package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

import sg.edu.iss.team6.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s from Student s")
    ArrayList<Student> findAllStudents();
}

