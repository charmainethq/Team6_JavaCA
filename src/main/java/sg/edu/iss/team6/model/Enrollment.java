package sg.edu.iss.team6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Enrollment implements Serializable {
    @Id
    private long enrollmentId;


    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="class_id")
    private CourseClass courseClass;

    @Column(columnDefinition = "ENUM('SUBMITTED', 'CONFIRMED','COMPLETED', 'WITHDRAWN,'FAILED'")
    @Enumerated(EnumType.STRING)
    private EnrollmentEnum enrollmentStatus;

    @Column(nullable = true)
    private long score;
}
