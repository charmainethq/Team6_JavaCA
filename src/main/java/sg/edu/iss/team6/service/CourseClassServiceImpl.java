package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repository.CourseClassRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseClassServiceImpl implements CourseClassService {
	@Autowired
	private CourseClassRepository ccRepo;

	@Override
	public List<Long> findByLecturerId(long lecId) {
		List<Long> ccList = ccRepo.findByLecturerId(lecId);
		return ccList;
	}


}
