package sg.edu.iss.team6.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Lecturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lecturerId;


    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @Size(min=2, max=30, message="Name must be 2-30 characters long")
    private String firstName;

    @Size(min=2, max=30, message="Name must be 2-30 characters long")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min=8, max=15, message="Contact number should contain 8-15 numbers")
    private String contactNo;

    @Size(min=2, max=90, message="Address must be 2-90 characters long")
    private String address;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.REMOVE)
    private List<CourseClass> CourseClasses;

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
