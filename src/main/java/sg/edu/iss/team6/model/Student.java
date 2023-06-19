package sg.edu.iss.team6.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String contactNo;
    private String address;
    private long gpa;
    
    
    @OneToMany(mappedBy="student",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Enrollment> studentEnrollments;

    public String getFullName(){
        return firstName + " " + lastName;
    }


}
