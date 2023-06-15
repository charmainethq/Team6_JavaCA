package sg.edu.iss.team6.model;
import javax.persistence.*;
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

    private String firstName;
    private String lastName;
    private String email;
    private int contactNo;
    private String address;
    private long gpa;

    @OneToMany(mappedBy="student")
    private List<Enrollment> studentEnrollments;


}
