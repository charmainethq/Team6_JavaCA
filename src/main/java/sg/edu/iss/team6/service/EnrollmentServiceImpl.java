package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.repository.CourseRepository;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Resource
    EnrollmentRepository erepo;

    @Resource
    CourseRepository crepo;

    @Override
    @Transactional
    public List<Enrollment> findAll(){
        return erepo.findAll();
    }

}
