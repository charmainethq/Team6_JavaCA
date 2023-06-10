package sg.edu.iss.team6.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private courseClass courseClass;


    private long score;
    private int enrollmentStatus;
}
