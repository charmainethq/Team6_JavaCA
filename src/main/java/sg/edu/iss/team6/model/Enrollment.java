package sg.edu.iss.team6.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollmentId;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name="class_id")
    private CourseClass courseClass;

    @Column(columnDefinition = "ENUM('SUBMITTED', 'CONFIRMED', 'COMPLETED', 'WITHDRAWN', 'FAILED', 'REMOVED')")
    @Enumerated(EnumType.STRING)
    private EnrollmentEnum enrollmentStatus;

    @Column(nullable = true)
    private Long score;

    @Column(name = "submitted_date")
    private LocalDateTime submittedDate;


}
