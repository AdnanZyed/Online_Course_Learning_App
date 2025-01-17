package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseLessonsDao {

    @Insert
    void insert(CourseLessons courseLesson);

    @Update
    void update(CourseLessons courseLesson);
    @Query("SELECT COUNT(*) FROM CourseLessons")
    int getTotalLessonsCount();

    @Query("SELECT COUNT(*) FROM CourseLessons WHERE l_completed = 1")
    int getCompletedLessonsCount();

    @Query("SELECT SUM(l_time) FROM CourseLessons")
    int getTotalLessonsTime();

    @Query("SELECT COUNT(*) FROM CourseLessons WHERE Course_ID = :courseId")
    int getTotalLessonsCountByCourseId(int courseId);

    @Query("SELECT COUNT(*) FROM CourseLessons WHERE Course_ID = :courseId AND l_completed = 1")
    int getCompletedLessonsCountByCourseId(int courseId);

    @Query("SELECT SUM(l_time) FROM CourseLessons WHERE Course_ID = :courseId")
    int getTotalLessonsTimeByCourseId(int courseId);

    @Delete
    void delete(CourseLessons courseLesson);

    @Query("SELECT * FROM CourseLessons WHERE Course_ID = :courseId")
    LiveData<List<CourseLessons>> getLessonsByCourseId(int courseId);

    @Query("UPDATE CourseLessons SET l_completed = :isCompleted WHERE l_id = :lessonId")
    void updateLessonCompletionStatus(int lessonId, boolean isCompleted);
}
