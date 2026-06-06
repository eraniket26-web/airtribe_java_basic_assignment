package com.airtribe.learntrack.entity;

/**
 * Student entity representing a student in the LearnTrack system
 * Extends Person and adds student-specific attributes
 */
public class Student extends Person {
    private int id;
    private String batch;
    private boolean active;

    public Student() { }

    public Student(String email) {
        super(email);
    }

    public Student(String lastName, String firstName, boolean active, String batch) {
        super(firstName, lastName);
        this.active = active;
        this.batch = batch;
    }

    /**
     * Set student ID with validation
     * @param id The student ID (must be positive)
     * @throws IllegalArgumentException if ID is not positive
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID: " + id);
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        if (batch == null || batch.trim().isEmpty()) {
            throw new IllegalArgumentException("Batch cannot be empty");
        }
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
