package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.constants.EnrollmentStatus;
import com.airtribe.learntrack.entity.Enrollment;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentRepository {

    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EnrollmentRepository.class);


    public void addEnrollment(Enrollment enrollment) {

        if(enrollment == null){
           throw new IllegalArgumentException("Enrollment cannot be null");
        }
        enrollments.add(enrollment);
        logger.info("Enrollment added: {}", enrollment);

    }


    public Enrollment checkEnrollment(int studentId, int courseId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudentId() == studentId && enrollment.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
    }


    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    /**
     * Get an enrollment by ID
     */
    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() == enrollmentId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Mark an enrollment as completed
     */
    public void markEnrollmentCompleted(int enrollmentId, int studentId, EnrollmentStatus.Status status) {
        enrollments.stream()
                .filter(enrollment -> (enrollment.getId() == enrollmentId && enrollment.getStudentId() == studentId))
                .findFirst()
                .ifPresent(enrollment -> {
                    enrollment.setStatus(status);
                    logger.info("Enrollment ID {} marked as {}: {}", enrollmentId, status, enrollment);
                });
    }

    /**
     * Mark an enrollment as cancelled
     */
    public void markEnrollmentCancelled(int enrollmentId, int studentId, EnrollmentStatus.Status status) {
        enrollments.stream()
                .filter(enrollment -> (enrollment.getId() == enrollmentId && enrollment.getStudentId() == studentId))
                .findFirst()
                .ifPresent(enrollment -> {
                    enrollment.setStatus(status);
                    logger.info("Enrollment ID {} marked as {}: {}", enrollmentId, status, enrollment);
                });
    }
}



