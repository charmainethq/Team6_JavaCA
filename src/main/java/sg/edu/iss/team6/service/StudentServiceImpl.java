package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;


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
    public Student findByUser(User user) {
        return srepo.findByUser(user);
    }

    @Override
    public Student create(Student student) {
        return srepo.save(student);
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return srepo.save(student);
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
    public List<Enrollment> getStudentEnroll(long studentId){
        Student student = srepo.findByStudentId(studentId);

        List<Enrollment> enrols = student.getStudentEnrollments();

        return enrols;
    }

    @Override
    @Transactional
    public double computeStudentavgScore(long studentId){

        double avge = 0;

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
            avge = score/credit;
        }

        return avge;
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
    public Map<Course, String> getCourseandScore(long studentId){

        List<Enrollment> enrollments = getCompletedEnrollmentsForStudent(studentId);
        Map<Course, String> courseAndScoreMap = new HashMap<>();

        for (Enrollment enrollment : enrollments) {
            Course course = enrollment.getCourseClass().getCourse();
            String score = enrollment.getScore().toString();
            courseAndScoreMap.put(course, score);
        }

        return courseAndScoreMap;
    }



}

