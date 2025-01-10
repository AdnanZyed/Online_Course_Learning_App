package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.onlinecourseampe_learningapp.Student;
import com.example.onlinecourseampe_learningapp.Teacher;

@Entity(
        tableName = "Student_Teacher",
        foreignKeys = {
                @ForeignKey(
                        entity = Teacher.class,
                        parentColumns = "USER_Name",
                        childColumns = "Teacher_USER_Name",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "USER_Name",
                        childColumns = "Student_USER_Name",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = "Teacher_USER_Name"), // إضافة فهرس هنا
                @Index(value = "Student_USER_Name") // إضافة فهرس آخر
        }
)
public class Student_Teacher {
    @NonNull
    @ColumnInfo(name = "Student_USER_Name")
    public String studentUserName;

    @NonNull
    @ColumnInfo(name = "Teacher_USER_Name")
    public String teacherUserName;

    public Student_Teacher(@NonNull String studentUserName, @NonNull String teacherUserName) {
        this.studentUserName = studentUserName;
        this.teacherUserName = teacherUserName;
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
                "studentUserName='" + studentUserName + '\'' +
                ", teacherUserName='" + teacherUserName + '\'' +
                '}';
    }
}
