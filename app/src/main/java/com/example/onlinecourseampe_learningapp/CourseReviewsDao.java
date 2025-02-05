package com.example.onlinecourseampe_learningapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseReviewsDao {

    @Insert
    void insertReview(Course_Reviews review);

    @Query("DELETE FROM Course_Reviews WHERE s_u_name = :studentUserName")
    void deleteReviewByStudent(String studentUserName);

    @Query("UPDATE Course_Reviews SET comment = :newComment, rating = :newRating WHERE s_u_name = :studentUserName")
    void updateReviewByStudent(String studentUserName, String newComment, float newRating);

    @Query("SELECT * FROM Course_Reviews WHERE Course_ID_R = :courseId")
    LiveData<List<Course_Reviews>> getAllReviewsByCourseId(int courseId);
}