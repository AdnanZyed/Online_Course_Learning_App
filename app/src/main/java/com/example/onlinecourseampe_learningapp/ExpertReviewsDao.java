package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpertReviewsDao {

    @Insert
    void insertReviewT(Expert_Reviews review);

    @Query("DELETE FROM Expert_Reviews WHERE s_u_name = :farmerUserName")
    void deleteReviewByFarmerT(String farmerUserName);

    @Query("UPDATE Expert_Reviews SET comment = :newComment, rating = :newRating WHERE s_u_name = :farmerUserName")
    void updateReviewByFarmerT(String farmerUserName, String newComment, float newRating);

    @Query("SELECT * FROM Expert_Reviews WHERE Teatur_USER_Name = :expertuser")
    LiveData<List<Expert_Reviews>> getAllReviewsBySeasonIdT(String expertuser);
}