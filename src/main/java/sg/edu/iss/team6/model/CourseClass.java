package sg.edu.iss.team6.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CourseClass implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long classId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Date startDate;
    private int size;
    private int confirmed;
    private String roomNum;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToOne
    @JoinColumn(name="enrollment_id")
    private Enrollment classEnrollment;


}
