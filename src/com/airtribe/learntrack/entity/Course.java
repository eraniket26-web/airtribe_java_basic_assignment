package com.airtribe.learntrack.entity;

public class Course {

    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course() { }

    public Course(int id, boolean active, int durationInWeeks, String description, String courseName) {
        this.id = id;
        this.active = active;
        this.durationInWeeks = durationInWeeks;
        this.description = description;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", durationInWeeks=" + durationInWeeks +
                '}';
    }
}
