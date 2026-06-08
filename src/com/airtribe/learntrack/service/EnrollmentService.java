package com.airtribe.learntrack.service;

import com.airtribe.learntrack.constants.EnrollmentStatus;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * Service layer for managing Enrollment operations
 * Handles business logic for student enrollments in courses
 */
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);
    private final EnrollmentRepository repository = new EnrollmentRepository();

    public EnrollmentService() {
        logger.debug("EnrollmentService initialized");
    }

    /**
     * Enroll a student in a course
     * @param studentId The ID of the student
     * @param courseId The ID of the course
     * @throws IllegalArgumentException if student is already enrolled in the course
     */
    public void enrollStudent(int studentId, int courseId) {
        try {
            // Check if student is already enrolled
            Enrollment existingEnrollment = repository.checkEnrollment(studentId, courseId);
            if (existingEnrollment != null) {
                throw new IllegalArgumentException("Student is already enrolled in this course");
            }

            // Create new enrollment
            Enrollment enrollment = new Enrollment();
            enrollment.setId(IdGenerator.generateNextEnrollmentId());
            enrollment.setStudentId(studentId);
            enrollment.setCourseId(courseId);
            enrollment.setEnrollmentDate(LocalDate.now().toString());
            enrollment.setStatus(EnrollmentStatus.Status.ACTIVE);

            repository.addEnrollment(enrollment);
            logger.info("Student ID {} enrolled successfully in Course ID {}", studentId, courseId);
        } catch (IllegalArgumentException e) {
            logger.error("Error enrolling student: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Get all enrollments for a specific student
     * @param studentId The ID of the student
     * @return List of enrollments for the student
     */
    public List<Enrollment> getStudentEnrollments(int studentId) {
        try {
            List<Enrollment> enrollments = repository.getEnrollmentsByStudentId(studentId);
            if (enrollments.isEmpty()) {
                logger.info("No enrollments found for student ID {}", studentId);
            } else {
                logger.info("Found {} enrollments for student ID {}", enrollments.size(), studentId);
            }
            return enrollments;
        } catch (Exception e) {
            logger.error("Error fetching enrollments for student ID {}: {}", studentId, e.getMessage());
            throw e;
        }
    }

    /**
     * Mark an enrollment as completed
     * @param enrollmentId The ID of the enrollment
     * @param studentId The ID of the student
     * @throws EntityNotFoundException if enrollment not found
     */
    public void markEnrollmentAsCompleted(int enrollmentId, int studentId) {
        try {
            Enrollment enrollment = repository.getEnrollmentById(enrollmentId);
            if (enrollment == null || enrollment.getStudentId() != studentId) {
                throw new EntityNotFoundException("Enrollment not found for Student ID " + studentId);
            }

            repository.markEnrollmentCompleted(enrollmentId, studentId, EnrollmentStatus.Status.COMPLETED);
            logger.info("Enrollment ID {} marked as completed for Student ID {}", enrollmentId, studentId);
        } catch (EntityNotFoundException e) {
            logger.error("Error marking enrollment as completed: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Mark an enrollment as cancelled
     * @param enrollmentId The ID of the enrollment
     * @param studentId The ID of the student
     * @throws EntityNotFoundException if enrollment not found
     */
    public void markEnrollmentAsCancelled(int enrollmentId, int studentId) {
        try {
            Enrollment enrollment = repository.getEnrollmentById(enrollmentId);
            if (enrollment == null || enrollment.getStudentId() != studentId) {
                throw new EntityNotFoundException("Enrollment not found for Student ID " + studentId);
            }

            repository.markEnrollmentCancelled(enrollmentId, studentId, EnrollmentStatus.Status.CANCELLED);
            logger.info("Enrollment ID {} marked as cancelled for Student ID {}", enrollmentId, studentId);
        } catch (EntityNotFoundException e) {
            logger.error("Error marking enrollment as cancelled: {}", e.getMessage());
            throw e;
        }
    }
}
