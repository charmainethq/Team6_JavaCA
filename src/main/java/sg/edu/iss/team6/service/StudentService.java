package sg.edu.iss.team6.service;

import sg.edu.iss.team6.model.*;
import java.util.List;


import java.util.*;

public interface StudentService {
    ArrayList<Student> findAllStudents();

    Student findByuser(User u);
    Student findByStudentId(Long studentId);
    
    void updateStudent(Student student);

    List<Enrollment> getCompletedEnrollmentsForStudent(long studentId);

    List<Course> getStudentcourse(long studentId);

    long computeStudentgpa(long studentId);

    Map<String, Long> getCourseandScore(long studentId);


}
