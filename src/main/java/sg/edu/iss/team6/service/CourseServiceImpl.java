package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.repository.CourseClassRepository;
import sg.edu.iss.team6.repository.CourseRepository;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Resource
	private CourseRepository cseRepo;
	
	@Override
	public Course findById(long id) {
		return cseRepo.findById(id).orElse(null);
	}
}
