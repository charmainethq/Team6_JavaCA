package sg.edu.iss.team6.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import sg.edu.iss.team6.model.*;
import sg.edu.iss.team6.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;



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
    public double computeStudentgpa(long studentId){
        double avge = computeStudentavgScore(studentId);
        int ten = 0;
        double tmp = avge/10;
        switch((int)tmp){
            case 10:
                ten = 5;
                break;
            case 9:
                ten = 4;
                break;
            case 8:
                ten = 3;
                break;
            case 7:
                ten = 2;
                break;
            case 6:
                ten = 1;
                break;
            default:
                ten = 0;
        }

        return (ten + (avge%10)/10.0);
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
    public String[][] getCourseandScore(long studentId){

        List<Enrollment> enrol= getCompletedEnrollmentsForStudent(studentId);

        String[][] CourseandScore = new String[enrol.size()][enrol.size()];



        for (int i=0;i<enrol.size();i++){
            String[] temp = new String[2];
            temp[0] = enrol.get(i).getCourseClass().getCourse().getName();
            temp[1] = enrol.get(i).getScore().toString();
            CourseandScore[i] = temp;
        }

        return CourseandScore;



    /**@Override
    @Transactional
    public Student findByUser(User u) {
        return srepo.findByUserUsername(u);
<<<<<<< HEAD
=======
>>>>>>> 6c61760a29bcd135c8b04597cfdb6261c03542be
>>>>>>> 3fdb0fcbf0715fe98cdfc78b6bb44fb1936fcebb
    }

    @Override
    @Transactional
<<<<<<< HEAD
=======
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
>>>>>>> 3fdb0fcbf0715fe98cdfc78b6bb44fb1936fcebb
    public Student findByUser(User u) {
        return srepo.findByUser(u);
    }
    **/


}
}

