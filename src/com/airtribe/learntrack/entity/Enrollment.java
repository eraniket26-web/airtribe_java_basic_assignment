package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.constants.EnrollmentStatus;

public class Enrollment {

    //Fields: id, studentId, courseId, enrollmentDate, status (e.g., "ACTIVE", "COMPLETED", "CANCELLED" as String or simple enum if you want slightly advanced)
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private EnrollmentStatus.Status status;


    public Enrollment() {}

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, EnrollmentStatus.Status status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus.Status getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus.Status status) {
        this.status = status;
    }
}


