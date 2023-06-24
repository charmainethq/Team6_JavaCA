package sg.edu.iss.team6.service;


import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.*;

import java.util.ArrayList;


public interface StudentService {
    ArrayList<Student> findAllStudents();

    Student findByStudentId(Long studentId);

    Student findByUser(User user);

    Student findByUserUsername(String username);

    Student create(Student student);
    Student update(Student student);
    void delete(long id);

    List<Enrollment> getCompletedEnrollmentsForStudent(long studentId);
    List<Course> getStudentcourse(long studentId);



    double computeStudentavgScore(long studentId);
    String[][] getCourseandScore(long studentId);
    List<Enrollment> getStudentEnroll(long studentId);


}
