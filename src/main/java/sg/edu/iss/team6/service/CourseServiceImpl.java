package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Admin;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.Student;
import sg.edu.iss.team6.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository cRepo;

    @Override
    public List<Course> findAllCourses() {
        return cRepo.findAll();
    }

    @Override
    public Course findByCourseId(long id) {
        return cRepo.findByCourseId(id);
    }

    @Override
    public Course create(Course course) {
        return cRepo.save(course);
    }

    @Override
    public Course update(Course course) {
        return cRepo.save(course);
    }

    @Override
    @Transactional
    public void delete(long id) {
        cRepo.deleteByCourseId(id);
    }



    @Override
    public List<Course> getAllCourses(){
        return cRepo.findAll();
    }

    @Override
    public Course findCourseByCourseId(Long courseId){
        return cRepo.findCourseByCourseId(courseId);

    }
    
	@Override
	public Course findById(long id) {
		return cRepo.findById(id).orElse(null);
	}
}
