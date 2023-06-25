package sg.edu.iss.team6.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import sg.edu.iss.team6.model.*;

import sg.edu.iss.team6.repository.CourseClassRepository;

import sg.edu.iss.team6.repository.EnrollmentRepository;

import java.util.ArrayList;

import sg.edu.iss.team6.repository.StudentRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Resource
    EnrollmentRepository eRepo;

    @Autowired
    CourseClassRepository ccRepository;

    @Autowired
    StudentRepository sRepo;

    @Override
    @Transactional
    public Optional<Enrollment> findByStudentAndClass(long classId, long studentId){
        return Optional.ofNullable(eRepo.findByCourseClassClassIdAndStudentStudentId(classId, studentId));
    }


    public void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum newStatus) {
        Optional<Enrollment> enrollmentOptional = eRepo.findById(enrollmentId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            enrollment.setEnrollmentStatus(newStatus);
            eRepo.save(enrollment);
        } else {
            throw new IllegalArgumentException("Enrollment not found with ID: " + enrollmentId);
        }
    }

	@Override
	@Transactional
	public Enrollment findById(long id) {
		return eRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Enrollment update(Enrollment currentEnrollment) {
		return eRepo.save(currentEnrollment);
	}

	@Override
	@Transactional
	public ArrayList<Enrollment> findByClassId(long classId){
		return eRepo.findEnrollmentByCourseID(classId);
	}
    


    @Override
    public List<Enrollment> findAllEnrollments() {
        return eRepo.findAll();
    }
    @Override
    public  List<Enrollment> findByCourseClass(CourseClass courseClass){
        return eRepo.findByCourseClass(courseClass);
    }

    @Override
    public List<Enrollment> findByStudent(Student student) {
        return eRepo.findByStudent(student);
    }

    @Override
    public Enrollment findByEnrollmentId(long id) {
        Optional<Enrollment> enrollmentOptional = eRepo.findById(id);
        return enrollmentOptional.orElse(null);
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        return eRepo.save(enrollment);
    }

    @Override
    public void delete(long id) {
        eRepo.deleteById(id);
    }
    @Override
    public void deleteList(List<Enrollment> enrollments){
        eRepo.deleteAll(enrollments);
    }
}
