package com.example.onlinecourseampe_learningapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface Student_Dao {
    @Insert
    Void insertStudent(Student student);

    @Update
    Void updateStudent(Student student);

    @Delete
    Void deleteStudent(Student student);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM Student WHERE Student_user_name=:student_user_name")
    LiveData<List<Student>> getAllStudentsByUser(String student_user_name);



}
