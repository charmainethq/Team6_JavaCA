package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.repository.CourseClassRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseClassServiceImpl implements CourseClassService{
	@Resource
	private CourseClassRepository cseClsRepo;
	
	@Override
	public CourseClass findById(long id) {
		return cseClsRepo.findById(id).orElse(null);
	}
	
	public List<Long> findDistinctCourseId(long lecturerId){
		return cseClsRepo.findDistinctCourseId(lecturerId);
	}
	public ArrayList<CourseClass> findByLecturerId(long lecturerId){
		return cseClsRepo.findByLecturerId(lecturerId);
	}
}
