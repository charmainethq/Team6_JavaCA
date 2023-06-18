package sg.edu.iss.team6.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.CourseRepository;
import sg.edu.iss.team6.repository.EnrollmentRepository;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }
    @Override
    public  List<Enrollment> findByCourseClass(CourseClass courseClass){
        return enrollmentRepository.findByCourseClass(courseClass);
    }

    @Override
    public List<Enrollment> findByStudent(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    @Override
    public Enrollment findByEnrollmentId(long id) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);
        return enrollmentOptional.orElse(null);
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(long id) {
        enrollmentRepository.deleteById(id);
    }
    @Override
    public void deleteList(List<Enrollment> enrollments){
        enrollmentRepository.deleteAll(enrollments);
    }

    @Override
    public void updateEnrollmentStatus(long enrollmentId, EnrollmentEnum newStatus) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            enrollment.setEnrollmentStatus(newStatus);
            enrollmentRepository.save(enrollment);
        } else {
            throw new IllegalArgumentException("Enrollment not found with ID: " + enrollmentId);
        }
    }

    @Override
    public void removeStudentFromCourse(long studentId, long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByStudentAndCourse(student, course);

        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();

            // Remove the enrollment from the student's enrollments list
            student.getStudentEnrollments().remove(enrollment);

            // Remove the enrollment from the course's enrollments list
            course.getEnrollments().remove(enrollment);

            // Delete the enrollment entity
            enrollmentRepository.delete(enrollment);
        } else {
            throw new IllegalArgumentException("Enrollment not found for student ID: " + studentId + " and course ID: " + courseId);
        }
    }
}
