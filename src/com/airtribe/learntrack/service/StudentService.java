package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.airtribe.learntrack.constants.AppConstants.STUDENT_ID_MUST_BE_POSITIVE;
import static com.airtribe.learntrack.constants.AppConstants.STUDENT_NOT_FOUND;

/**
 * Service layer for managing Student operations
 * Handles business logic and validation
 */
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository repository = new StudentRepository();

    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        List<Student> studentList = repository.getAllStudents();
        if (studentList.isEmpty()) {
            logger.info("Student records are empty!!");
        }
        return studentList;
    }

    /**
     * Add a new student with validation
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        // Validate student data
        if (!InputValidator.isValidFirstName(student.getFirstName())) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (!InputValidator.isValidLastName(student.getLastName())) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (!InputValidator.isValidEmail(student.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!InputValidator.isValidBatch(student.getBatch())) {
            throw new IllegalArgumentException("Invalid batch");
        }

        student.setId(IdGenerator.generateNextStudentId());
        logger.info("ID generated for student: {}", student.getId());
        repository.addStudent(student);
        logger.info("Student added successfully: {}", student);
    }

    /**
     * Get student by ID
     */
    public Student getStudentById(int id) {
        if (!InputValidator.isValidPositiveInteger(id)) {
            throw new IllegalArgumentException(STUDENT_ID_MUST_BE_POSITIVE);
        }

        Student student = repository.getStudentById(id);

        if (student == null) {
            String message = STUDENT_NOT_FOUND.concat(String.valueOf(id));
            logger.error(message);
            throw new EntityNotFoundException(message);
        }

        logger.info("Student retrieved: {}", student.getId());
        return student;
    }

    /**
     * Deactivate a student
     */
    public void deactivateStudent(int id) {
        if (!InputValidator.isValidPositiveInteger(id)) {
            throw new IllegalArgumentException(STUDENT_ID_MUST_BE_POSITIVE);
        }

        Student student = repository.deActivateStudent(id);
        if (student == null) {
            throw new EntityNotFoundException(STUDENT_NOT_FOUND.concat(String.valueOf(id)));
        }

        logger.info("Student with ID {} deactivated successfully", id);
    }

}
