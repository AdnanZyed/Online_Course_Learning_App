package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Student_Teacher_Dao {

        @Insert
        void insertStudentTeacher(Student_Teacher studentTeacher);

        @Delete
        void deleteStudentTeacher(Student_Teacher studentTeacher);

        @Query("SELECT * FROM Student_Teacher WHERE Student_user_name = :studentUsername")
        LiveData<List<Student_Teacher>> getTeachersByStudent(String studentUsername);

        @Query("SELECT * FROM Student_Teacher WHERE Teacher_USER_Name = :teacherUsername")
        LiveData<List<Student_Teacher>> getStudentsByTeacher(String teacherUsername);
    }


