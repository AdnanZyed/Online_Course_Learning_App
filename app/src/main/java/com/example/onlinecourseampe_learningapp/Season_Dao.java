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
public interface Season_Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Void insertSeason(Season season);

    @Update
    Void updateSeason(Season season);

    @Query("SELECT * FROM Season WHERE isBookmarked = 1")
    LiveData<List<Season>> getBookmarkedSeasons();


    @Query("UPDATE Season SET isAddCart = :isAddCart WHERE Season_ID = :seasonId")
    void updateAddStatus(int seasonId, boolean isAddCart);

    @Query("SELECT * FROM Season WHERE Season_NAME LIKE :seasonName")
    LiveData<List<Season>> getSeasonName(String seasonName);

    @Query("UPDATE Season SET isBookmarked = :isBookmarked WHERE Season_ID = :seasonId")
    void updateBookmarkStatus(int seasonId, boolean isBookmarked);

    @Query("SELECT * FROM Season WHERE Season_NAME LIKE :seasonName")
    LiveData<List<Season>> getSeasonByName(String seasonName);

    @Delete
    Void deleteSeason(Season season);

    @Query("SELECT * FROM Season WHERE Categorie = :categorie")
    LiveData<List<Season>> getSeasonByCategory(String categorie);

    @Query("SELECT * FROM Season")
    LiveData<List<Season>> getAllSeason();

    @Query("SELECT * FROM Season WHERE Season_ID=:season_Id")
    LiveData<List<Season>> getAllSeasonsById(int season_Id);

    @Query("SELECT * FROM Season WHERE Expert_USER_Name =:expert_USER_Name")
    LiveData<List<Season>> getAllSeasonsByExpert_USER_Name(String expert_USER_Name);

    @Query("SELECT * FROM Season WHERE Season_ID IN (:SeasonIds)")
    LiveData<List<Season>> getSeasonsByIds(List<Integer> SeasonIds);


}



