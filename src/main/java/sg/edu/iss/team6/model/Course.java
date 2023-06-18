package sg.edu.iss.team6.model;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long courseId;

    private int courseNum;
    private String name;
    private String description;
    private int credits;
    private int duration;

    @OneToMany(mappedBy = "course")
    private List<CourseClass> courseClasses;

}
