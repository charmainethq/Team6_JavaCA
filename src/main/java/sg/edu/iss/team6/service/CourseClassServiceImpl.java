package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.repository.CourseClassRepository;
import java.util.List;


@Service
public class CourseClassServiceImpl implements CourseClassService{

    @Resource
    private CourseClassRepository classRepo;

    @Override
    public List<CourseClass> findByCourseId(Long courseId) {
        //System.out.println(classRepo.findByCourseId(courseId).size());
        return classRepo.findAllByCourseCourseId(courseId);
    }

    @Override
    public CourseClass findByClassId(Long classId){
        return classRepo.findByClassId(classId);
    }






}
