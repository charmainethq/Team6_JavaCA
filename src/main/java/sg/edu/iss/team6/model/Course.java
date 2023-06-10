package sg.edu.iss.team6.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


    private int courseNum;
    private String name;
    private String description;
    private int credits;
    private int duration;

    @OneToMany(mappedBy = "course")
    private List<courseClass> courseClassList;

}
