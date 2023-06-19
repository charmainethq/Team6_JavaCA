package sg.edu.iss.team6.service;



import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student findByStudentId(Long studentId);

    Student create(Student student);
    Student update(Student student);
    void delete(long id);
    Student findByuser(User u);


    void updateStudent(Student student);

    List<Enrollment> getCompletedEnrollmentsForStudent(long studentId);

    List<Course> getStudentcourse(long studentId);

    long computeStudentgpa(long studentId);

    Map<String, Long> getCourseandScore(long studentId);
}
