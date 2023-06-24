package sg.edu.iss.team6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {



    Student findByStudentId(Long studentId);
    Student findByUserUsername(User u);

    Student findByUserUsername(String username);

    void deleteByStudentId(long id);


    Student findByUser(User u);


}

