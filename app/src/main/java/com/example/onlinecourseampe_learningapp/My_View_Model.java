package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class My_View_Model extends AndroidViewModel {
    private LiveData<List<Notification>> allNotifications;

    private My_Repository repository;


    private final LiveData<List<Course_Reviews>> allReviews;
    private final LiveData<List<Teacher_Reviews>> allReviewsT;


    public My_View_Model(@NonNull Application application) {
        super(application);
        repository = new My_Repository(application);
        allReviews = null;
        allReviewsT = null;
        allNotifications = repository.getAllNotifications();

    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    public void addNotification(String title, String message, int iconResId) {
        repository.insert(new Notification(title, message, iconResId));
    }

    public void insertStudentLesson(StudentLesson studentLesson) {
        repository.insertStudentLesson(studentLesson);
    }

    public LiveData<List<StudentLesson>> getCompletedLessonsForStudent(String studentUserName) {
        return repository.getCompletedLessonsForStudent(studentUserName);
    }

    public void updateCompletionStatus(String studentUserName, int lessonId, boolean completed) {
        repository.updateCompletionStatus(studentUserName, lessonId, completed);
    }

    public void deleteStudentLesson(String studentUserName, int lessonId) {
        repository.deleteStudentLesson(studentUserName, lessonId);
    }

    public void insertMessage(Message message) {
        repository.insertMessage(message);
    }

    public LiveData<List<Message>> getMessagesBetweenUsers(String currentUser, String otherUser) {
        return repository.getMessagesBetweenUsers(currentUser, otherUser);
    }

    public LiveData<List<Student>> getAllStudentsExceptCurrent(String currentUsername) {
        return repository.getAllStudentsExceptCurrent(currentUsername);
    }

    public LiveData<Message> getLastMessageForUser(String username) {
        return repository.getLastMessageForUser(username);
    }
//

    public void insertCourseLesson(CourseLessons courseLesson) {
        repository.insertCourseLesson(courseLesson);
    }

    public LiveData<Integer> getTotalLessonsCount() {
        return repository.getTotalLessonsCount();
    }

    public LiveData<Integer> getCompletedLessonsCount() {
        return repository.getCompletedLessonsCount();
    }

    public LiveData<Integer> getTotalLessonsTime() {
        return repository.getTotalLessonsTime();
    }

    public void updateCourseLesson(CourseLessons courseLesson) {
        repository.updateCourseLesson(courseLesson);
    }

    public void deleteCourseLesson(CourseLessons courseLesson) {
        repository.deleteCourseLesson(courseLesson);
    }

    public LiveData<Integer> getTotalLessonsCountByCourseId(int courseId) {
        return repository.getTotalLessonsCountByCourseId(courseId);
    }

    public LiveData<Integer> getCompletedLessonsCountByCourseId(int courseId) {
        return repository.getCompletedLessonsCountByCourseId(courseId);
    }

    public LiveData<Integer> getTotalLessonsTimeByCourseId(int courseId) {
        return repository.getTotalLessonsTimeByCourseId(courseId);
    }

    public LiveData<List<CourseLessons>> getLessonsByCourseId(int courseId) {
        return repository.getLessonsByCourseId(courseId);
    }

    public void updateLessonCompletionStatus(int lessonId, boolean isCompleted) {
        repository.updateLessonCompletionStatus(lessonId, isCompleted);
    }

    public void insertReview(Course_Reviews review) {
        repository.insertReview(review);
    }

    public void deleteReviewByStudent(String studentUserName) {
        repository.deleteReviewByStudent(studentUserName);
    }

    public void updateReviewByStudent(String studentUserName, String newComment, float newRating) {
        repository.updateReviewByStudent(studentUserName, newComment, newRating);
    }

    public LiveData<List<Course_Reviews>> getAllReviewsByCourseId(int courseId) {
        return repository.getAllReviewsByCourseId(courseId);
    }


    public void insertReviewT(Teacher_Reviews review) {
        repository.insertReviewT(review);
    }

    public void deleteReviewByStudentT(String studentUserName) {
        repository.deleteReviewByStudentT(studentUserName);
    }

    public void updateReviewByStudentT(String studentUserName, String newComment, float newRating) {
        repository.updateReviewByStudentT(studentUserName, newComment, newRating);
    }

    public LiveData<List<Teacher_Reviews>> getAllReviewsByCourseIdT(String teacher) {
        return repository.getAllReviewsByCourseIdT(teacher);
    }


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

    public LiveData<List<Course>> getBookmarkedCourses() {
        return repository.getBookmarkedCourses();
    }

    public LiveData<List<Student_Course>> getBookmarkedCoursesByStudent(String studentUsername) {
        return repository.getBookmarkedCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getisAddCartCoursesByStudent(String studentUsername) {
        return repository.getisAddCartCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getBookmarkedCoursesByStudent1(String studentUsername, int courseId) {
        return repository.getBookmarkedCoursesByStudent1(studentUsername, courseId);

    }

    public LiveData<List<Student_Course>> getAddCartCoursesByStudent1(String studentUsername, int courseId) {
        return repository.getAddCartCoursesByStudent1(studentUsername, courseId);

    }

    public LiveData<List<Student_Course>> getisRatingCoursesByStudent1(String studentUsername, int courseId) {
        return repository.getisRatingCoursesByStudent1(studentUsername, courseId);
    }

    public LiveData<List<Student_Course>> getisRegisterCoursesByStudent1(String studentUsername) {
        return repository.getisRegisterCoursesByStudent1(studentUsername);
    }

    public LiveData<Void> deleteStudentCourseByUserAndCourse(String studentUsername, int courseId) {
        return repository.deleteStudentCourseByUserAndCourse(studentUsername, courseId);
    }

    public LiveData<List<Course>> updateBookmarkStatusAndGetCourses(int courseId, boolean isBookmarked) {
        return repository.updateBookmarkStatusAndGetCourses(courseId, isBookmarked);
    }

    public LiveData<List<Course>> updateisAddCartStatusAndGetCourses(int courseId, boolean isAddCart) {
        return repository.updateisAddCartStatusAndGetCourses(courseId, isAddCart);
    }


    public LiveData<List<Course>> getCoursesByCategory(String category) {
        return repository.getCoursesByCategory(category);
    }

    LiveData<List<Course>> getAllCoursesById(int id) {
        return repository.getAllCoursesById(id);
    }

    public LiveData<List<Course>> getAllCoursesByIds(List<Integer> courseIds) {
        return repository.getCoursesByIds(courseIds);
    }

    LiveData<List<Course>> getAllCoursesByTeacher_USER_Name(String Teacher_USER_Name) {
        return repository.getAllCoursesByTeacher_USER_Name(Teacher_USER_Name);
    }

    public LiveData<Boolean> isStudentCourseExists(String studentUsername, int courseId, boolean isRegister) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isStudentCourseExists(studentUsername, courseId, isRegister);
            result.postValue(exists);
        });
        return result;
    }

    public LiveData<Boolean> isStudentCourseExistsC(String studentUsername, int courseId, boolean isAddCart) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isStudentCourseExists1(studentUsername, courseId, isAddCart);
            result.postValue(exists);
        });
        return result;
    }

    public LiveData<Boolean> isStudentCourseExistsB(String studentUsername, int courseId, boolean isBookmark) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isStudentCourseExistsB(studentUsername, courseId, isBookmark);
            result.postValue(exists);
        });
        return result;
    }

    public void insertStudentCourse(Student_Course studentCourse) {
        repository.insertStudentCourse(studentCourse);
    }

    public void updateCourseStudent(Student_Course studentCourse) {
        repository.updateCourseStudent(studentCourse);
    }

    public LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername) {
        return repository.getCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getStudentsByCourse(int courseId) {
        return repository.getStudentsByCourse(courseId);
    }

    public LiveData<List<Student_Course>> getStudentsByCourseAndStudent(String user, int courseId) {
        return repository.getStudentsByCourseAndStudent(user, courseId);
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


    public LiveData<List<Teacher>> searchTeachers(String teacherName) {
        return repository.searchTeachers(teacherName);
    }

    public LiveData<List<Course>> searchCourses(String courseName) {
        return repository.searchCourses(courseName);
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