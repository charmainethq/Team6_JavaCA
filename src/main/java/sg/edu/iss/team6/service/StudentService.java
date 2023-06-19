package sg.edu.iss.team6.service;

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.*;

public interface StudentService {
    ArrayList<Student> findAllStudents();

    Student findByStudentId(Long studentId);
    Student findByUserUsername(String username);

    Student create(Student student);
    void update(Student student);
    void delete(long id);

    List<Enrollment> getCompletedEnrollmentsForStudent(long studentId);
    List<Course> getStudentcourse(long studentId);
    long computeStudentgpa(long studentId);
    Map<String, Long> getCourseandScore(long studentId);




}
