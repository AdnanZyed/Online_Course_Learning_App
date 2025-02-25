package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface Teacher_Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertTeacher(Teacher teacher);

    @Update
    void updateTeacher(Teacher teacher);

    @Delete
    void deleteTeacher(Teacher teacher);

    @Query("SELECT * FROM Teacher WHERE Teatur_name LIKE :teaturName")
    LiveData<List<Teacher>> getTeacherByName(String teaturName);

    @Query("SELECT * FROM Teacher")
    LiveData<List<Teacher>> getAllTeachers();

    @Query("SELECT * FROM Teacher WHERE Teatur_USER_Name=:teacher_user_name")
    LiveData<List<Teacher>> getAllTeachersByUser(String teacher_user_name);


}
