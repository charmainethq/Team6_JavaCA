package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentId(Long studentId);


}

