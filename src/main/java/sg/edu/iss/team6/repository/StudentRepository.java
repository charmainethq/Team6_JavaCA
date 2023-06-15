package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;

import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s from Student s")
    ArrayList<Student> findAllStudents();

    Student findByUser(User u);
}

