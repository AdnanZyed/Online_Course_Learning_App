package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SeasonReviewsDao {

    @Insert
    void insertReview(Season_Reviews review);

    @Query("DELETE FROM Season_Reviews WHERE s_u_name = :farmerUserName")
    void deleteReviewByFarmer(String farmerUserName);

    @Query("UPDATE Season_Reviews SET comment = :newComment, rating = :newRating WHERE s_u_name = :farmerUserName")
    void updateReviewByFarmer(String farmerUserName, String newComment, float newRating);

    @Query("SELECT * FROM Season_Reviews WHERE Season_ID_R = :seasonId")
    LiveData<List<Season_Reviews>> getAllReviewsBySeasonId(int seasonId);
}