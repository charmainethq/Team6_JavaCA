package sg.edu.iss.team6.service;
import sg.edu.iss.team6.model.Course;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface CourseService {

	Course findCourseById(Long score);
}
