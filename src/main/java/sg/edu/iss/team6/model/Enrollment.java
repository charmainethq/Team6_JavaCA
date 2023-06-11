package sg.edu.iss.team6.model;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollmentId;

    @ManyToOne
    @JoinColumn(name="student_user_id")
    private Student student;

    @OneToOne(mappedBy="classEnrollment")
    @JoinColumn(name="class_id")
    private CourseClass courseClass;

    @Column(columnDefinition = "ENUM('SUBMITTED', 'CONFIRMED','COMPLETED', 'WITHDRAWN,'FAILED'")
    @Enumerated(EnumType.STRING)
    private EnrollmentEnum enrollmentStatus;

    private long score;
}
