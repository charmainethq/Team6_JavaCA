package sg.edu.iss.team6.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int contactNo;
    private String address;
    private long gpa;

    @ManyToMany
    private List<StudentEnrollment> enrolledClasses;

}
