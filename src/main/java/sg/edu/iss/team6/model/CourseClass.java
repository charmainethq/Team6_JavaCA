package sg.edu.iss.team6.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
public class courseClass {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Course course;

    private Date startDate;
    private int size;
    private int enrollment;
    private String roomNum;

    @ManyToOne
    private Lecturer lecturer;

    @ManyToMany
    private List<Student> students;

}
