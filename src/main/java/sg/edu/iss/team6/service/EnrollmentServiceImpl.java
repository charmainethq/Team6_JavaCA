package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Resource
    EnrollmentRepository eRepo;

    @Override
    @Transactional
    public Optional<Enrollment> findByStudentAndClass(long classId, long studentId){
        return Optional.ofNullable(eRepo.findByCourseClassClassIdAndStudentStudentId(classId, studentId));
    }

    @Override
    @Transactional
    public void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum status){
        eRepo.updateEnrollmentStatus(enrollmentId, status);
    }

    @Override
    @Transactional
    public List<Enrollment> findAll(){
        return erepo.findAll();
    }

}
