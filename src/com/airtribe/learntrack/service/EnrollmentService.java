package com.airtribe.learntrack.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service layer for managing Enrollment operations
 * Handles business logic for student enrollments in courses
 */
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);

    public EnrollmentService() {
        logger.debug("EnrollmentService initialized");
    }
}
