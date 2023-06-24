package sg.edu.iss.team6.model;
import javax.persistence.*;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "student_id")
    private long studentId;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @Size(min=2, max=30, message="Name must be 2-30 characters long")
    private String firstName;

    @Size(min=2, max=30, message="Name must be 2-30 characters long")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min=2, max=10, message="Contact Number must be 2-10 characters long")
    private String contactNo;

    @Size(min=2, max=50, message="Address must be 2-30 characters long")
    private String address;

    private double gpa;

    @OneToMany(mappedBy="student")
    @JsonIgnore
    private List<Enrollment> studentEnrollments;

    public String getFullName(){
        return firstName + " " + lastName;
    }


}
