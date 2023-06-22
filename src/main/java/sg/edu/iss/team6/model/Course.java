package sg.edu.iss.team6.model;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long courseId;
    @Min(value=101, message = "Invalid format")
    private int courseNum;
    @Size(min=2, max=50, message="Name should be 2-50 characters long")
    private String name;
    private String description;
    private int credits;
    @Min(value=1, message="Duration should be at least 1")
    private int duration;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<CourseClass> courseClasses;

}
