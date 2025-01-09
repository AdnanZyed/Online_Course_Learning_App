package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudentTeacher",
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "Student_user_name", childColumns = "Student_user_name", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Teacher.class, parentColumns = "Teatur_USER_Name", childColumns = "Teacher_USER_Name", onDelete = ForeignKey.CASCADE)
        })
public class Student_Teacher {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Student_user_name;
    private String Teacher_USER_Name;

    public Student_Teacher(String student_user_name, String teacher_USER_Name) {
        Student_user_name = student_user_name;
        Teacher_USER_Name = teacher_USER_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_user_name() {
        return Student_user_name;
    }

    public void setStudent_user_name(String student_user_name) {
        Student_user_name = student_user_name;
    }

    public String getTeacher_USER_Name() {
        return Teacher_USER_Name;
    }

    public void setTeacher_USER_Name(String teacher_USER_Name) {
        Teacher_USER_Name = teacher_USER_Name;
    }

    @Override
    public String toString() {
        return "Student_Teacher{" +
                "id=" + id +
                ", Student_user_name='" + Student_user_name + '\'' +
                ", Teacher_USER_Name='" + Teacher_USER_Name + '\'' +
                '}';
    }
}

