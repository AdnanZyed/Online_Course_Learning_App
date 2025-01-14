package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudentCourse",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "Student_user_name", childColumns = "Student_user_name", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class, parentColumns = "Course_ID", childColumns = "Course_ID", onDelete = ForeignKey.CASCADE)
        })
public class Student_Course {

    @PrimaryKey(autoGenerate = true)
    private int id_sc;
    private String Student_user_name;
    private int Course_ID;

    public Student_Course(int id_sc, String student_user_name, int course_ID) {
        this.id_sc = id_sc;
        Student_user_name = student_user_name;
        Course_ID = course_ID;
    }


    public Student_Course() {
    }

    public int getId_sc() {
        return id_sc;
    }

    public void setId_sc(int id_sc) {
        this.id_sc = id_sc;
    }

    public String getStudent_user_name() {
        return Student_user_name;
    }

    public void setStudent_user_name(String student_user_name) {
        Student_user_name = student_user_name;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    @Override
    public String toString() {
        return "Student_Course{" +
                "id_sc=" + id_sc +
                ", Student_user_name='" + Student_user_name + '\'' +
                ", Course_ID=" + Course_ID +
                '}';
    }
}


