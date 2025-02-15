package com.example.onlinecourseampe_learningapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Student_Course_Dao {


    @Query("SELECT COUNT(*) FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId  AND  isRegister = :isRegister")
    int isStudentCourseExists(String studentUsername, int courseId, boolean isRegister);

    @Query("SELECT COUNT(*) FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId  AND  isAddCart = :isAddCart")
    int isStudentCourseExists1(String studentUsername, int courseId, boolean isAddCart);

    @Query("SELECT COUNT(*) FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId  AND  isBookmark = :isBookmark")
    int isStudentCourseExistsB(String studentUsername, int courseId, boolean isBookmark);

    @Update
    void updateCourseStudent(Student_Course studentCourse);

    @Insert
    void insertStudentCourse(Student_Course studentCourse);

    @Delete
    void deleteStudentCourse(Student_Course studentCourse);

    @Query("UPDATE StudentCourse SET isBookmark = 0 WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark= 1")
    void deleteStudentCourseByUserAndCourse(String studentUsername, int courseId);

    @Query("DELETE FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark= 1")
    void delete1StudentCourseByUserAndCourse(String studentUsername, int courseId);

    @Query("UPDATE Course SET isBookmarked = :isBookmarked WHERE Course_ID = :courseId")
    void updateBookmarkStatus(int courseId, boolean isBookmarked);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername")
    LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND isBookmark = 1")
    LiveData<List<Student_Course>> getBookmarkedCoursesByStudent(String studentUsername);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND isAddCart =1")
    LiveData<List<Student_Course>> getisAddCartCoursesByStudent(String studentUsername);


    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isBookmark = 1 ")
    LiveData<List<Student_Course>> getBookmarkedCoursesByStudent1(String studentUsername, int courseId);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND isAddCart= 1")
    LiveData<List<Student_Course>> getisAddCartCoursesByStudent1(String studentUsername, int courseId);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername AND Course_ID = :courseId AND rating!=0 OR rating!=NULL")
    LiveData<List<Student_Course>> getisRatingCoursesByStudent1(String studentUsername, int courseId);

    @Query("SELECT * FROM StudentCourse WHERE Student_user_name = :studentUsername   AND isRegister= 1")
    LiveData<List<Student_Course>> getisRegisterCoursesByStudent1(String studentUsername);

    @Query("SELECT * FROM StudentCourse WHERE Course_ID = :courseId")
    LiveData<List<Student_Course>> getStudentsByCourse(int courseId);

    @Query("SELECT * FROM StudentCourse WHERE Course_ID = :courseId AND Student_user_name= :studentCourse")
    LiveData<List<Student_Course>> getStudentsByCourseAndStudent(String studentCourse, int courseId);
}
