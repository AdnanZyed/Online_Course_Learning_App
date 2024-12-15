package com.example.onlinecourseampe_learningapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface Course_Dao {
    @Insert
    Void insertCourse(Course course);

    @Update
    Void updateCourse(Course course);

    @Delete
    Void deleteCourse(Course course);

    @Query("SELECT * FROM Course")
    LiveData<List<Course>> getAllCourse();

    @Query("SELECT * FROM Course WHERE Course_ID=:course_Id")
    LiveData<List<Course>> getAllCoursesById(int course_Id);



}
