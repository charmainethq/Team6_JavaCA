package sg.edu.iss.team6.service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import java.util.ArrayList;
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


}
