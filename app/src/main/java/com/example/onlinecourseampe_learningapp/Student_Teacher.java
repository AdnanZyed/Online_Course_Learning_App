package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.onlinecourseampe_learningapp.Student;
import com.example.onlinecourseampe_learningapp.Teacher;

@Entity(
        tableName = "Student_Teacher",
        foreignKeys = {
                @ForeignKey(
                        entity = Teacher.class,
                        parentColumns = "Teatur_USER_Name",
                        childColumns = "teacherUserName",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "Student_user_name",
                        childColumns = "studentUserName",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = "teacherUserName"), // إضافة فهرس هنا
                @Index(value = "studentUserName") // إضافة فهرس آخر
        }
)
public class Student_Teacher {
    @PrimaryKey(autoGenerate = true)
    private int id_st;
    @NonNull
    public String studentUserName;

    @NonNull
    public String teacherUserName;


    public Student_Teacher(int id_st, @NonNull String studentUserName, @NonNull String teacherUserName) {
        this.id_st = id_st;
        this.studentUserName = studentUserName;
        this.teacherUserName = teacherUserName;
    }

    public Student_Teacher() {
    }

    public int getId_st() {
        return id_st;
    }

    public void setId_st(int id_st) {
        this.id_st = id_st;
    }

    @NonNull
    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(@NonNull String studentUserName) {
        this.studentUserName = studentUserName;
    }

    @NonNull
    public String getTeacherUserName() {
        return teacherUserName;
    }

    public void setTeacherUserName(@NonNull String teacherUserName) {
        this.teacherUserName = teacherUserName;
    }

    @Override
    public String toString() {
        return "Student_Teacher{" +
                "id_st=" + id_st +
                ", studentUserName='" + studentUserName + '\'' +
                ", teacherUserName='" + teacherUserName + '\'' +
                '}';
    }
}
