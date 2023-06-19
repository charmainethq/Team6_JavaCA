package sg.edu.iss.team6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

    // values set during login
    private User user;
    private Student student = null;
    private Lecturer lecturer = null;
    private Admin admin = null;


}
