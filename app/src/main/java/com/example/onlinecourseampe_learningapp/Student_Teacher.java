package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "Student_Teacher",
        primaryKeys = {"Student_user_name", "Teatur_USER_Name"})
public class Student_Teacher {
    @NonNull
    private String Student_user_name;
    @NonNull
    private String Teatur_USER_Name;

    public Student_Teacher(@NonNull String student_user_name, @NonNull String teatur_USER_Name) {
        Student_user_name = student_user_name;
        Teatur_USER_Name = teatur_USER_Name;
    }

    // Getters and Setters
}
