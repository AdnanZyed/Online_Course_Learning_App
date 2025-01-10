package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Student_Course_Dao {

    @Insert
    void insertStudentCourse(Student_Course studentCourse);

    @Delete
    void deleteStudentCourse(Student_Course studentCourse);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername")
    LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername);

    @Query("SELECT * FROM StudentCourse WHERE Course_ID = :courseId")
    LiveData<List<Student_Course>> getStudentsByCourse(int courseId);
}
