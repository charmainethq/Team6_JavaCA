package sg.edu.iss.team6.service;
import java.util.List;

import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;

import java.util.ArrayList;
import java.util.List;

public interface LecturerService {

    Lecturer findByUser(User u);

    List<Lecturer> findAll();


    Lecturer findById(Long id);


    Lecturer create(Lecturer l);

    Lecturer update(Lecturer l);

    Long delete(Long id);

    int delete(int id);

    long delete(long id);

    List<Lecturer>findByUser_Username(String username);

}
