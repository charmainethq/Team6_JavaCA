package sg.edu.iss.team6.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.team6.model.Course;
import sg.edu.iss.team6.model.CourseClass;
import sg.edu.iss.team6.repository.CourseClassRepository;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseClassServiceImpl implements CourseClassService{
    @Autowired
    private CourseClassRepository ccRepo;
    @Override
    public List<CourseClass> findByCourse(Course course) {
        return ccRepo.findByCourse(course);
    }

    @Override
    public CourseClass findByClassId(long id) {
        return ccRepo.findByClassId(id);
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
    };

}
