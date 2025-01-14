package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class My_View_Model extends AndroidViewModel {

    private My_Repository repository;
    private final LiveData<List<Course_Reviews>> allReviews;

    public My_View_Model(@NonNull Application application) {
        super(application);
        repository = new My_Repository(application);
        allReviews = null; // سيتم تخصيصها عند استدعاء طريقة الجلب

    }
    // إضافة مراجعة
    public void insertReview(Course_Reviews review) {
        repository.insertReview(review);
    }

    // حذف مراجعة بناءً على اسم المستخدم
    public void deleteReviewByStudent(String studentUserName) {
        repository.deleteReviewByStudent(studentUserName);
    }

    // تحديث مراجعة بناءً على اسم المستخدم
    public void updateReviewByStudent(String studentUserName, String newComment, float newRating) {
        repository.updateReviewByStudent(studentUserName, newComment, newRating);
    }

    // جلب جميع المراجعات بناءً على معرف الدورة
    public LiveData<List<Course_Reviews>> getAllReviewsByCourseId(int courseId) {
        return repository.getAllReviewsByCourseId(courseId);
    }

    // دالة لحذف جميع الكورسات

    void insertCourse(Course course) {
        repository.insertCourse(course);


    }

    void updateCourse(Course course) {
        repository.updateCourse(course);


    }

    void deleteCourse(Course course) {
        repository.deleteCourse(course);


    }


    LiveData<List<Course>> getAllCourse() {
        return repository.getAllCourse();
    }
    // دالة لجلب الكورسات المفضلة باستخدام LiveData
    public LiveData<List<Course>> getBookmarkedCourses() {
        return repository.getBookmarkedCourses();  // إرجاع LiveData
    }
    public LiveData<List<Course>> updateBookmarkStatusAndGetCourses(int courseId, boolean isBookmarked) {
        return repository.updateBookmarkStatusAndGetCourses(courseId, isBookmarked);
    }


    // دالة لجلب الكورسات بناءً على التصنيف
    public LiveData<List<Course>> getCoursesByCategory(String category) {
        return repository.getCoursesByCategory(category);
    }

    LiveData<List<Course>> getAllCoursesById(int id) {
        return repository.getAllCoursesById(id);
    }
    LiveData<List<Course>> getAllCoursesByTeacher_USER_Name(String Teacher_USER_Name) {
        return repository.getAllCoursesByTeacher_USER_Name(Teacher_USER_Name);
    }
    public void insertStudentCourse(Student_Course studentCourse) {
        repository.insertStudentCourse(studentCourse);
    }

    public LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername) {
        return repository.getCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getStudentsByCourse(int courseId) {
        return repository.getStudentsByCourse(courseId);
    }

    void insertStudent(Student student) {

        repository.insertStudent(student);


    }

    void updateStudent(Student student) {
        repository.updateStudent(student);


    }

    void deleteStudent(Student student) {
        repository.deleteStudent(student);


    }

    LiveData<List<Student>> getAllStudent() {

        return repository.getAllStudent();
    }

    LiveData<List<Student>> getStudentByUsernameAndPassword(String username, String password) {
        return repository.getStudentByUsernameAndPassword(username, password);
    }
    LiveData<List<Student>> getAllStudentByUser(String student_user_name) {

        return repository.getAllStudentByUser(student_user_name);
    }






    void insertTeacher(Teacher teacher) {

        repository.insertTeacher(teacher);


    }

    void updateTeacher(Teacher teacher) {
        repository.updateTeacher(teacher);


    }

    void deleteTeacher(Teacher teacher) {
        repository.deleteTeacher(teacher);


    }

    LiveData<List<Teacher>> getAllTeacher() {

        return repository.getAllTeacher();
    }


    // دالة للبحث عن المعلمين بناءً على الاسم
    public LiveData<List<Teacher>> searchTeachers(String teacherName) {
        return repository.searchTeachers(teacherName);
    }
    LiveData<List<Teacher>> getAllTeacherByUser(String Teatur_USER_Name) {

        return repository.getAllTeacherByUser(Teatur_USER_Name);
    }
    public void insertStudentTeacher(Student_Teacher studentTeacher) {
        repository.insertStudentTeacher(studentTeacher);
    }

    public LiveData<List<Student_Teacher>> getTeachersByStudent(String studentUsername) {
        return repository.getTeachersByStudent(studentUsername);
    }

    public LiveData<List<Student_Teacher>> getStudentsByTeacher(String teacherUsername) {
        return repository.getStudentsByTeacher(teacherUsername);
    }

}