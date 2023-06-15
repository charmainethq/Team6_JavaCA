package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.User;

public interface LecturerService {

    Lecturer findByUser(User u);

}
