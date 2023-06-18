package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.repository.CourseRepository;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Resource
    private CourseRepository courseRepo;

    @Override
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }

    @Override
    public Course findCourseByCourseId(Long courseId){
        return courseRepo.findCourseByCourseId(courseId);
    }
}
