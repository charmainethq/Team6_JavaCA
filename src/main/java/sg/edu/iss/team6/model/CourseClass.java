package sg.edu.iss.team6.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    @Temporal(TemporalType.DATE)
    private Date startDate;
    private int size;
    private int confirmed;
    private String roomNum;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @JsonIgnore
    private Lecturer lecturer;

    @OneToMany(mappedBy="courseClass")
    private List<Enrollment> classEnrollment;

    public String getFormatStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(calendar.getTime());
    }

    public String getFormatEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, course.getDuration());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(calendar.getTime());
    }




}
