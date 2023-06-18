package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	@Resource
	private EnrollmentRepository enrlRepo;
	
	@Override
	public Enrollment findById(long id) {
		return enrlRepo.findById(id).orElse(null);
	}
	
	@Override
	public Enrollment update(Enrollment currentEnrollment) {
		return enrlRepo.save(currentEnrollment);
	}
	
	@Override
	public ArrayList<Enrollment> findByClassId(long classId){
		return enrlRepo.findEnrollmentByCourseID(classId);
	}
}
