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
public interface Course_Dao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Void insertCourse(Course course);

    @Update
    Void updateCourse(Course course);

    @Query("SELECT * FROM Course WHERE isBookmarked = 1")
    LiveData<List<Course>> getBookmarkedCourses();


    @Query("UPDATE Course SET isBookmarked = :isBookmarked WHERE Course_ID = :courseId")
    void updateBookmarkStatus(int courseId, boolean isBookmarked);
    // الاستعلام الجديد للبحث عن الكورس بناءً على الاسم
    @Query("SELECT * FROM Course WHERE Course_NAME LIKE :courseName")
    LiveData<List<Course>> getCourseByName(String courseName);
    @Delete
    Void deleteCourse(Course course);
    // إضافة استعلام جديد لعرض الكورسات بناءً على التصنيف
    @Query("SELECT * FROM Course WHERE Categorie = :categorie")
    LiveData<List<Course>> getCoursesByCategory(String categorie);

    @Query("SELECT * FROM Course")
    LiveData<List<Course>> getAllCourse();

    @Query("SELECT * FROM Course WHERE Course_ID=:course_Id")
    LiveData<List<Course>> getAllCoursesById(int course_Id);
    @Query("SELECT * FROM Course WHERE Teacher_USER_Name =:teacher_USER_Name")
    LiveData<List<Course>> getAllCoursesByTeacher_USER_Name(String teacher_USER_Name);
}



