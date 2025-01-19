package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeacherReviewsDao {

    // إضافة مراجعة
    @Insert
    void insertReviewT(Teacher_Reviews review);

    // حذف مراجعة بناءً على اسم المستخدم
    @Query("DELETE FROM Teacher_Reviews WHERE s_u_name = :studentUserName")
    void deleteReviewByStudentT(String studentUserName);

    // تحديث مراجعة بناءً على اسم المستخدم
    @Query("UPDATE Teacher_Reviews SET comment = :newComment, rating = :newRating WHERE s_u_name = :studentUserName")
    void updateReviewByStudentT(String studentUserName, String newComment, float newRating);

    // جلب كل المراجعات بناءً على معرف الدورة
    @Query("SELECT * FROM Teacher_Reviews WHERE Teatur_USER_Name = :teacheruser")
    LiveData<List<Teacher_Reviews>> getAllReviewsByCourseIdT(String teacheruser);
}