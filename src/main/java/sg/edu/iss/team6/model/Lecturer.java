package sg.edu.iss.team6.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int contactNo;
    private String address;

    @OneToMany(mappedBy="lecturer")
    private List<courseClass> courseClasses;

}
