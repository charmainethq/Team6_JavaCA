package sg.edu.iss.team6.model;


import javax.persistence.*;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Max(value=1000, message="Class size cannot exceed 1000")
    private int size;
    private String roomNum;
    private int confirmedNumber;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @JsonIgnore
    private Lecturer lecturer;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Enrollment> classEnrollment;

    @JsonIgnore
    public List<Enrollment> getClassEnrollment() {
        return classEnrollment;
    }

    public String getFormatStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    public String getFormatEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, course.getDuration());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return sdf.format(calendar.getTime());
    }



}
