package sg.edu.iss.team6.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Max(value=1000, message="Class size cannot exceed 1000")
    private int size;
    private String roomNum;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @JsonIgnore
    private Lecturer lecturer;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.REMOVE)
    private List<Enrollment> classEnrollment;

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

    public int getConfirmedNumber() {
        int confirmedCount = 0;
        for (Enrollment e : classEnrollment) {
            if (e.getEnrollmentStatus() == EnrollmentEnum.CONFIRMED) {
                confirmedCount++;
            }
        }
        return confirmedCount;
    }

}
