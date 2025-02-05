package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeacherReviewsDao {

    @Insert
    void insertReviewT(Teacher_Reviews review);

    @Query("DELETE FROM Teacher_Reviews WHERE s_u_name = :studentUserName")
    void deleteReviewByStudentT(String studentUserName);

    @Query("UPDATE Teacher_Reviews SET comment = :newComment, rating = :newRating WHERE s_u_name = :studentUserName")
    void updateReviewByStudentT(String studentUserName, String newComment, float newRating);

    @Query("SELECT * FROM Teacher_Reviews WHERE Teatur_USER_Name = :teacheruser")
    LiveData<List<Teacher_Reviews>> getAllReviewsByCourseIdT(String teacheruser);
}