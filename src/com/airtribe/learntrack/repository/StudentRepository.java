package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRepository {

    private static final List<Student> students = new ArrayList<>();


    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        students.add(student);
    }

    public Student getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Student deActivateStudent(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .peek(student -> student.setActive(false))
                .findFirst()
                .orElse(null);
    }
}



