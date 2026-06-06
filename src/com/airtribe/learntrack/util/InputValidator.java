package com.airtribe.learntrack.util;

/**
 * Utility class for validating user input across the application
 */
public class InputValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_COURSE_NAME_LENGTH = 100;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MAX_BATCH_LENGTH = 20;

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        return isNotEmpty(email) && email.matches(EMAIL_REGEX);
    }

    /**
     * Validate student first name
     */
    public static boolean isValidFirstName(String firstName) {
        return isValidName(firstName);
    }

    /**
     * Validate student last name
     */
    public static boolean isValidLastName(String lastName) {
        return isValidName(lastName);
    }

    /**
     * Generic name validation
     */
    private static boolean isValidName(String name) {
        return isNotEmpty(name) &&
               name.length() <= MAX_NAME_LENGTH &&
               name.matches("^[a-zA-Z\\s'-]+$");
    }

    /**
     * Validate batch/semester code
     */
    public static boolean isValidBatch(String batch) {
        return isNotEmpty(batch) && batch.length() <= MAX_BATCH_LENGTH;
    }

    /**
     * Validate course name
     */
    public static boolean isValidCourseName(String courseName) {
        return courseName.length() <= MAX_COURSE_NAME_LENGTH && isValidName(courseName);
    }

    /**
     * Validate course description
     */
    public static boolean isValidDescription(String description) {
        return isNotEmpty(description) &&
               description.length() <= MAX_DESCRIPTION_LENGTH;
    }

    /**
     * Validate if string is not null or empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validate positive integer
     */
    public static boolean isValidPositiveInteger(int num) {
        return num > 0;
    }
}
