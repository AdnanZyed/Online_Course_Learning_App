package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Student_Course_Dao {


    // التحقق من وجود كائن مطابق
    @Query("SELECT COUNT(*) FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark = :isBookmark AND isAddCart = :isAddCart AND isRegister = :isRegister")
    int isStudentCourseExists(String studentUsername, int courseId, boolean isBookmark, boolean isAddCart, boolean isRegister);

    @Insert
    void insertStudentCourse(Student_Course studentCourse);

    @Delete

    void deleteStudentCourse(Student_Course studentCourse);
    @Query("DELETE FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark= :isBookmark AND isAddCart=:isAddCart")
    void deleteStudentCourseByUserAndCourse(String studentUsername, int courseId, boolean isBookmark, boolean isAddCart);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername")
    LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND isBookmark = 1")
    LiveData<List<Student_Course>> getBookmarkedCoursesByStudent(String studentUsername);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND isAddCart =1")
    LiveData<List<Student_Course>> getisAddCartCoursesByStudent(String studentUsername);

//    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND isRegister = 1")
//    LiveData<List<Student_Course>> getisRegisterByStudent(String studentUsername);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark = 1 ")
    LiveData<List<Student_Course>> getBookmarkedCoursesByStudent1(String studentUsername,int courseId);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isAddCart= 1")
    LiveData<List<Student_Course>> getisAddCartCoursesByStudent1(String studentUsername,int courseId);
    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername  AND isRegister= 1")
    LiveData<List<Student_Course>> getisRegisterCoursesByStudent1(String studentUsername);
    @Query("SELECT * FROM StudentCourse WHERE Course_ID = :courseId")
    LiveData<List<Student_Course>> getStudentsByCourse(int courseId);
}
