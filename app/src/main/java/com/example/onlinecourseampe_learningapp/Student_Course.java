package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "Student_Course",
        primaryKeys = {"Student_user_name", "Course_ID"})
public class Student_Course {
    @NonNull
    private String Student_user_name;
    private int Course_ID;

    public Student_Course(@NonNull String student_user_name, int course_ID) {
        Student_user_name = student_user_name;
        Course_ID = course_ID;
    }

    // Getters and Setters
}
