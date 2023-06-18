package sg.edu.iss.team6.service;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Lecturer;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repository.LecturerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LecturerServiceImpl implements LecturerService {
	@Resource
	private LecturerRepository lecRepo;
	
	@Override
	public List<Lecturer> findAll(){
		return (List<Lecturer>)lecRepo.findAll();
	}

	@Override
	public Lecturer findById(long id) {
		return lecRepo.findById(id).orElse(null);
	}

	public Lecturer update(Lecturer lecturer) {
		return lecRepo.save(lecturer);
	}

}
