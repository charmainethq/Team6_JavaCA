package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;
import java.util.List;

public interface LecturerService {
    Lecturer findByUser(User u);

    List<Lecturer> findAll();

    Lecturer findById(int id);

    Lecturer create(Lecturer l);

    Lecturer update(Lecturer l);

    int delete(int id);
}
