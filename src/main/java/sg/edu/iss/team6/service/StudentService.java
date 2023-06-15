package sg.edu.iss.team6.service;

import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;

public interface StudentService {
    ArrayList<Student> findAllStudents();

    Student findByUser(User u);
}
