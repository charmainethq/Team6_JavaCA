package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.repository.CourseClassRepository;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	@Autowired
	private EnrollmentRepository enrollRepo;
	

//	ArrayList<Enrollment> findByClassId(long classId);
//	ArrayList<Enrollment> findEnrollmentByStudentID(int studentId);	
//	List<Enrollment> findStudentByClassId(long classId);
	
	@Override
	public ArrayList<Enrollment> findStudentByClassId(long classId) {
		ArrayList<Enrollment> enList = enrollRepo.findByClassId(classId);
		return enList;
	}

	@Override
	public ArrayList<Enrollment> findEnrollmentByStudentID(long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Enrollment> findByClassId(long classId) {
		return enrollRepo.findByClassId(classId);
	}
}



