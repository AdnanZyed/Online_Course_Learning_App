package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Farmer_Season_Dao {


    @Query("SELECT COUNT(*) FROM FARMER_SEASONS WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId  AND  isRegister = :isRegister")
    int isFarmerSeasonExists(String farmerUsername, int seasonId, boolean isRegister);

    @Query("SELECT COUNT(*) FROM FARMER_SEASONS WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId  AND  isAddCart = :isAddCart")
    int isFarmerSeasonExists1(String farmerUsername, int seasonId, boolean isAddCart);

    @Query("SELECT COUNT(*) FROM FARMER_SEASONS WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId  AND  isBookmark = :isBookmark")
    int isFarmerSeasonExistsB(String farmerUsername, int seasonId, boolean isBookmark);

    @Update
    void updateSeasonFarmer(Farmer_Seasons farmerSeason);

    @Insert
    void insertFarmerSeason(Farmer_Seasons farmerSeason);

    @Delete
    void deleteFarmerSeason(Farmer_Seasons farmerSeason);

    @Query("UPDATE Farmer_Seasons SET isBookmark = 0 WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId AND isBookmark= 1")
    void deleteFarmerSeasonByUserAndSeason(String farmerUsername, int seasonId);

    @Query("DELETE FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId AND isBookmark= 1")
    void delete1FarmerSeasonByUserAndSeason(String farmerUsername, int seasonId);

    @Query("UPDATE Season  SET isBookmarked = :isBookmarked WHERE Season_ID = :seasonId")
    void updateBookmarkStatus(int seasonId, boolean isBookmarked);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername")
    LiveData<List<Farmer_Seasons>> getSeasonsByFarmer(String farmerUsername);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND isBookmark = 1")
    LiveData<List<Farmer_Seasons>> getBookmarkedSeasonsByFarmer(String farmerUsername);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND isAddCart =1")
    LiveData<List<Farmer_Seasons>> getisAddCartSeasonsByFarmer(String farmerUsername);


    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId AND isBookmark = 1 ")
    LiveData<List<Farmer_Seasons>> getBookmarkedSeasonsByFarmer1(String farmerUsername, int seasonId);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId AND isAddCart= 1")
    LiveData<List<Farmer_Seasons>> getisAddCartSeasonsByFarmer1(String farmerUsername, int seasonId);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername AND Season_ID = :seasonId AND rating!=0 OR rating!=NULL")
    LiveData<List<Farmer_Seasons>> getisRatingSeasonsByFarmer1(String farmerUsername, int seasonId);

    @Query("SELECT * FROM Farmer_Seasons WHERE Farmer_user_name = :farmerUsername   AND isRegister= 1")
    LiveData<List<Farmer_Seasons>> getisRegisterSeasonsByFarmer1(String farmerUsername);

    @Query("SELECT * FROM Farmer_Seasons WHERE Season_ID = :seasonId")
    LiveData<List<Farmer_Seasons>> getFarmersBySeason(int seasonId);

    @Query("SELECT * FROM Farmer_Seasons WHERE Season_ID = :seasonId AND Farmer_user_name= :farmerSeason")
    LiveData<List<Farmer_Seasons>> getFarmersBySeasonAndFarmer(String farmerSeason, int seasonId);
}
