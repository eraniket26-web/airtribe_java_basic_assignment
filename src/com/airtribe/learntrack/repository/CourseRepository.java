package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;

import java.util.List;

public class CourseRepository {

    private static final List<Course> courses = new ArrayList<>();


    public List<Course> getAllCourses() {
        return List.copyOf(courses);
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        courses.add(course);
    }

     public Course enabledOrDisabledCourse(int id , boolean flag) {
       return courses.stream().
                  filter(course -> course.getId() == id)
                 .peek(course -> course.setActive(flag))
                 .findFirst()
                 .orElse(null);

     }
}
