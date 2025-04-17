package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SeasonStepsDao {

    @Insert
    void insert(SeasonStep seasonStep);

    @Update
    void update(SeasonStep seasonStep);

    @Query("SELECT COUNT(*) FROM SeasonSteps")
    int getTotalStepsCount();

    @Query("SELECT COUNT(*) FROM SeasonSteps WHERE s_completed = 1")
    int getCompletedStepsCount();

    @Query("SELECT SUM(s_time) FROM SeasonSteps")
    int getTotalStepsTime();

    @Query("SELECT COUNT(*) FROM SeasonSteps WHERE Season_ID = :seasonId")
    int getTotalStepsCountBySeasonId(int seasonId);

    @Query("SELECT COUNT(*) FROM SeasonSteps WHERE Season_ID = :seasonId AND s_completed = 1")
    int getCompletedStepsCountBySeasonId(int seasonId);

    @Query("SELECT SUM(s_time) FROM SeasonSteps WHERE Season_ID = :seasonId")
    int getTotalStepsTimeBySeasonId(int seasonId);

    @Delete
    void delete(SeasonStep seasonStep);

    @Query("SELECT * FROM SeasonSteps WHERE Season_ID = :seasonId")
    LiveData<List<SeasonStep>> getStepsBySeasonId(int seasonId);

    @Query("UPDATE SeasonSteps SET s_completed = :isCompleted WHERE s_id = :stepId")
    void updateStepCompletionStatus(int stepId, boolean isCompleted);
}
