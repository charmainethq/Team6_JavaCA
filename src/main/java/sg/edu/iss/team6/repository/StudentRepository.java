package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStudentId(Long studentId);
    Student findByUserUsername(User u);
    void deleteByStudentId(long id);


    @Query("SELECT s from Student s")
    ArrayList<Student> findAllStudents();

    Student findByUser(User u);

}

