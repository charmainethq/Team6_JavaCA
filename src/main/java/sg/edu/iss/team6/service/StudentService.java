package sg.edu.iss.team6.service;

import sg.edu.iss.team6.model.*;
import java.util.ArrayList;

public interface StudentService {

    ArrayList<Student> findAllStudents();

    Student findByUsername(User u);

}
