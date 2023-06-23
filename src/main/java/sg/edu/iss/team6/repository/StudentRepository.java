package sg.edu.iss.team6.repository;

import java.util.ArrayList;
import java.util.function.LongBinaryOperator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


import org.springframework.data.repository.query.Param;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {



    Student findByStudentId(Long studentId);
    Student findByUserUsername(User u);

    Student findByUserUsername(String username);

    void deleteByStudentId(long id);


    Student findByUser(User u);


}

