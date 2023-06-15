package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;
public interface LecturerService {
    Lecturer findByUsername(User u);
}
