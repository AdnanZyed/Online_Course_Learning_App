package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentLessonDao {

    @Insert
    void insertStudentLesson(StudentLesson studentLesson);

    @Query("SELECT * FROM StudentLesson WHERE studentUserName = :studentUserName AND completed = 1 ")
    LiveData<List<StudentLesson>> getCompletedLessonsForStudent(String studentUserName);

    @Query("UPDATE StudentLesson SET completed = :completed WHERE studentUserName = :studentUserName AND lessonId = :lessonId")
    void updateCompletionStatus(String studentUserName, int lessonId, boolean completed);

    @Query("DELETE FROM StudentLesson WHERE studentUserName = :studentUserName AND lessonId = :lessonId")
    void deleteStudentLesson(String studentUserName, int lessonId);
}
