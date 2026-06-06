package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.CourseNotFoundException;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.airtribe.learntrack.constants.AppConstants.*;

/**
 * Service layer for managing Course operations
 * Handles business logic and validation for courses
 */
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository repository = new CourseRepository();

    public CourseService() {
        logger.debug("CourseService initialized");
    }

    public List<Course> getAllCourses() {

        List<Course> courseList = repository.getAllCourses();
        if(courseList.isEmpty()){
            logger.info("Course records are empty!!");
        }

        return courseList;
    }


    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        if(!InputValidator.isValidCourseName(course.getCourseName())){
            throw new IllegalArgumentException("Invalid course name");
        }

        if (!InputValidator.isValidDescription(course.getDescription())) {
            throw new IllegalArgumentException("Invalid course description");
        }

        if(!InputValidator.isValidPositiveInteger(course.getDurationInWeeks())){
            throw new IllegalArgumentException("Course duration must be a positive integer");
        }

        course.setCourseName(course.getCourseName().trim());
        course.setDescription(course.getDescription().trim());
        course.setId(IdGenerator.generateNextCourseId());
        course.setDurationInWeeks(course.getDurationInWeeks());
        logger.info("ID generated for course: {}", course.getId());
        logger.info("Course added successfully: {}", course.getCourseName());

        repository.addCourse(course);
    }

     public void enabledOrDisabledCourse(int id , boolean flag) {

         if (!InputValidator.isValidPositiveInteger(id)) {
             throw new IllegalArgumentException(COURSE_ID_MUST_BE_POSITIVE);
         }

         Course course = repository.enabledOrDisabledCourse(id, flag);
         if (course == null) {
             throw new CourseNotFoundException(COURSE_NOT_FOUND.concat(String.valueOf(id)));
         }

         repository.enabledOrDisabledCourse(id, flag);
     }


}
