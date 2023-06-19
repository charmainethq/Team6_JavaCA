package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Enrollment;
import sg.edu.iss.team6.model.EnrollmentEnum;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.StudentRepository;
import java.util.ArrayList;



@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentRepository srepo;

    @Resource
    StudentRepository urepo;

    @Override
    public ArrayList<Student> findAllStudents() {
        return (ArrayList<Student>) srepo.findAll();
    }

    @Override
    @Transactional
    public Student findByStudentId(Long studentId) {
        return srepo.findByStudentId(studentId);
    }

    @Override
    public Student create(Student student) {
        return srepo.save(student);
    }

    @Override
    public void update(Student student) {
        srepo.save(student);
    }

    @Override
    @Transactional
    public void delete(long id) {
        srepo.deleteByStudentId(id);
    }
    @Override
    @Transactional
    public List<Course> getStudentcourse(long studentId){

        Student student = srepo.findByStudentId(studentId);
        List<Course> crol = new ArrayList<>();
        if (student != null) {
            crol =  student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .map(e -> e.getCourseClass().getCourse())
                    .collect(Collectors.toList());
        }

        return crol;

    }

    @Override
    @Transactional
    public long computeStudentgpa(long studentId){

        long gpa = 0;

        Student student = srepo.findByStudentId(studentId);
        List<Enrollment> enrol = new ArrayList<>();

        if (student != null) {
            enrol =  student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .collect(Collectors.toList());
        }

        List<Course> courses = new ArrayList<>();

        long score = 0;
        int credit = 0;

        for (Enrollment enrollment:enrol){
            Course c = enrollment.getCourseClass().getCourse();
            courses.add(c);
            score = score + c.getCredits()*enrollment.getScore();
            credit = credit + c.getCredits();
        }

        if(credit != 0){
            gpa = score/credit;
        }

        return gpa;
    }

    @Override
    @Transactional
    public List<Enrollment> getCompletedEnrollmentsForStudent(long studentId){
        Student student = srepo.findByStudentId(studentId);
        if (student != null) {
            return student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Student findByUserUsername(String username){
        Student student = srepo.findByUserUsername(username);
        return student;
    }




    @Override
    @Transactional
    public Map<String, Long> getCourseandScore(long studentId){
        Map<String, Long> CourseandScore = new HashMap<>();

        List<Enrollment> enrol= getCompletedEnrollmentsForStudent(studentId);

        for(Enrollment enrollment : enrol){
            CourseandScore.put(enrollment.getCourseClass().getCourse().getName(),enrollment.getScore());
        }

        return CourseandScore;


    /**@Override
    @Transactional
    public Student findByUser(User u) {
        return srepo.findByUserUsername(u);
>>>>>>> 6c61760a29bcd135c8b04597cfdb6261c03542be
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public List<Course> getStudentcourse(long studentId){

        Student student = srepo.findByStudentId(studentId);
        List<Course> crol = new ArrayList<>();
        if (student != null) {
            crol =  student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .map(e -> e.getCourseClass().getCourse())
                    .collect(Collectors.toList());
        }

        return crol;

    }

    @Override
    @Transactional
    public long computeStudentgpa(long studentId){

        long gpa = 0;
        
        Student student = srepo.findByStudentId(studentId);
        List<Enrollment> enrol = new ArrayList<>();

        if (student != null) {
            enrol =  student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .collect(Collectors.toList());
        }

        List<Course> courses = new ArrayList<>();

        long score = 0;
        int credit = 0;

        for (Enrollment enrollment:enrol){
            Course c = enrollment.getCourseClass().getCourse();
            courses.add(c);
            score = score + c.getCredits()*enrollment.getScore();
            credit = credit + c.getCredits();
        }

        if(credit != 0){
            gpa = score/credit;
        }

        return gpa;
    }

    @Override
    @Transactional
    public List<Enrollment> getCompletedEnrollmentsForStudent(long studentId){
        Student student = srepo.findByStudentId(studentId);
        if (student != null) {
            return student.getStudentEnrollments().stream()
                    .filter(e -> e.getEnrollmentStatus() == EnrollmentEnum.COMPLETED)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Student findByUserUsername(String username){
        Student student = srepo.findByUserUsername(username);
        return student;
    }
=======
    public Student findByUser(User u) {
        return srepo.findByUser(u);
    }
    **/

    }

}
