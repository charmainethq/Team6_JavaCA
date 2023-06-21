package sg.edu.iss.team6.service;
import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.repository.CourseClassRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseClassServiceImpl implements CourseClassService{


    @Resource
    private CourseClassRepository classRepo;
    @Autowired
    private CourseClassRepository ccRepo;

    @Override
    public ArrayList<CourseClass> findByCourseId(long courseId) {
    	return ccRepo.findByCourseId(courseId);
    }
    
    @Override
    public CourseClass findById(long id) {
    	return ccRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<CourseClass> findByCourse(Course course) {
        return ccRepo.findByCourse(course);
    }

    @Override
    public CourseClass findByClassId(long id) {
        return ccRepo.findByClassId(id);
    }

    @Override
    public Page<CourseClass> findByCourseId(Long courseId,Pageable pageable) {
        return classRepo.findAllByCourseCourseId(courseId,pageable);
    }

    @Override
    public List<CourseClass> findByCourseId(Long courseId) {
        return classRepo.findAllByCourseCourseId(courseId);
    }

    @Override
    public CourseClass findByClassId(Long classId){
        return classRepo.findByClassId(classId);
    }



	public List<Long> findDistinctCourseId(long lecturerId){
		return classRepo.findDistinctCourseId(lecturerId);
	}
	public ArrayList<CourseClass> findByLecturerId(long lecturerId){
		return classRepo.findByLecturerId(lecturerId);
	}


    @Override
    public CourseClass create(CourseClass c) {
        return ccRepo.save(c);
    }

    @Override
    public CourseClass update(CourseClass c) {
        return ccRepo.save(c);
    }

    @Override
    @Transactional
    public void delete(long id) {
        ccRepo.deleteByClassId(id);
    }

}
