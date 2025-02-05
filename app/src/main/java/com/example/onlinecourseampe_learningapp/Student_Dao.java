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

public interface Student_Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Void insertStudent(Student student);

    @Update
    Void updateStudent(Student student);

    @Query("SELECT * FROM Student WHERE Student_user_name != :currentUsername")
    LiveData<List<Student>> getAllStudentsExcept(String currentUsername);

    @Delete
    Void deleteStudent(Student student);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudents();

    @Query("SELECT * FROM Student WHERE Student_user_name=:student_user_name")
    LiveData<List<Student>> getAllStudentsByUser(String student_user_name);


    @Query("SELECT * FROM Student WHERE Student_user_name = :username AND Student_password = :password")
    LiveData<List<Student>> getStudentByUsernameAndPassword(String username, String password);

}
