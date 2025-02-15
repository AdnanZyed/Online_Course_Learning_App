package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_Repository {

    private StudentLessonDao studentLessonDao;

    private Student_Course_Dao studentCourseDao;
    private Student_Teacher_Dao studentTeacherDao;
    private Teacher_Dao teacherDao;
    private Student_Dao studentDao;
    private Course_Dao courseDao;
    private CourseReviewsDao courseReviewsDao;
    private TeacherReviewsDao teacherReviewsDao;
    private CourseLessonsDao courseLessonsDao;
    private final NotificationDao notificationDao;
    private final LiveData<List<Notification>> allNotifications;
    private final MessageDao messageDao;
    private final ExecutorService executorService;

    private LiveData<List<Teacher>> AllTeacher;
    private LiveData<List<Student>> AllStudents;
    private LiveData<List<Course>> AllCourse;


    My_Repository(Application application) {
        My_Database db = My_Database.getDatabase(application);
        studentDao = db.studentDao();
        courseDao = db.courseDao();
        teacherDao = db.teacherDao();
        courseReviewsDao = db.courseReviewsDao();
        teacherReviewsDao = db.teacherReviewsDao();
        studentCourseDao = db.studentCourseDao();
        studentTeacherDao = db.studentTeacherDao();
        courseLessonsDao = db.courseLessonsDao();
        messageDao = db.messageDao();
        executorService = Executors.newSingleThreadExecutor();
        this.studentLessonDao = db.studentLessonDao();
        notificationDao = db.notificationDao();
        allNotifications = notificationDao.getAllNotifications();


    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    public void insert(Notification notification) {
        executorService.execute(() -> notificationDao.insert(notification));
    }

    public void insertStudentLesson(StudentLesson studentLesson) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentLessonDao.insertStudentLesson(studentLesson);
        });
    }

    public void deleteStudentLesson(String studentUserName, int lessonId) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentLessonDao.deleteStudentLesson(studentUserName, lessonId);
        });
    }

    public void updateCompletionStatus(String studentUserName, int lessonId, boolean completed) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentLessonDao.updateCompletionStatus(studentUserName, lessonId, completed);
        });
    }

    public LiveData<List<StudentLesson>> getCompletedLessonsForStudent(String studentUserName) {
        return studentLessonDao.getCompletedLessonsForStudent(studentUserName);
    }


    public void insertMessage(Message message) {
        My_Database.databaseWriteExecutor.execute(() -> messageDao.insertMessage(message));
    }

    public LiveData<List<Message>> getMessagesBetweenUsers(String currentUser, String otherUser) {
        return messageDao.getMessagesBetweenUsers(currentUser, otherUser);
    }

    public LiveData<Message> getLastMessageForUser(String username) {
        return messageDao.getLastMessageForUser(username);
    }

    public LiveData<List<Student>> getAllStudentsExceptCurrent(String currentUsername) {
        return studentDao.getAllStudentsExcept(currentUsername);
    }

    public LiveData<Integer> getTotalLessonsCountByCourseId(int courseId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int count = courseLessonsDao.getTotalLessonsCountByCourseId(courseId);
            result.postValue(count);
        });
        return result;
    }

    public LiveData<Integer> getCompletedLessonsCountByCourseId(int courseId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int count = courseLessonsDao.getCompletedLessonsCountByCourseId(courseId);
            result.postValue(count);
        });
        return result;
    }

    public LiveData<Integer> getTotalLessonsTimeByCourseId(int courseId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int totalTime = courseLessonsDao.getTotalLessonsTimeByCourseId(courseId);
            result.postValue(totalTime);
        });
        return result;
    }

    public void insertCourseLesson(CourseLessons courseLesson) {
        My_Database.databaseWriteExecutor.execute(() -> {

            courseLessonsDao.insert(courseLesson);

        });

    }


    public void updateCourseLesson(CourseLessons courseLesson) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseLessonsDao.update(courseLesson);

        });


    }

    public LiveData<Integer> getTotalLessonsCount() {
        MutableLiveData<Integer> totalLessonsCount = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            totalLessonsCount.postValue(courseLessonsDao.getTotalLessonsCount());
        });
        return totalLessonsCount;
    }

    public LiveData<Integer> getCompletedLessonsCount() {
        MutableLiveData<Integer> completedLessonsCount = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            completedLessonsCount.postValue(courseLessonsDao.getCompletedLessonsCount());
        });
        return completedLessonsCount;
    }

    public LiveData<Integer> getTotalLessonsTime() {
        MutableLiveData<Integer> totalLessonsTime = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            totalLessonsTime.postValue(courseLessonsDao.getTotalLessonsTime());
        });
        return totalLessonsTime;
    }


    public void deleteCourseLesson(CourseLessons courseLesson) {
        My_Database.databaseWriteExecutor.execute(() -> {

            courseLessonsDao.delete(courseLesson);
        });
    }

    public LiveData<List<CourseLessons>> getLessonsByCourseId(int courseId) {
        return courseLessonsDao.getLessonsByCourseId(courseId);
    }

    public void updateLessonCompletionStatus(int lessonId, boolean isCompleted) {
        My_Database.databaseWriteExecutor.execute(() -> {

            courseLessonsDao.updateLessonCompletionStatus(lessonId, isCompleted);
        });

    }

    void insertReview(Course_Reviews review) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseReviewsDao.insertReview(review);
        });
    }

    void updateReviewByStudent(String studentUserName, String newComment, float newRating) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseReviewsDao.updateReviewByStudent(studentUserName, newComment, newRating);
        });
    }

    void deleteReviewByStudent(String studentUserName) {
        My_Database.databaseWriteExecutor.execute(() -> {

            courseReviewsDao.deleteReviewByStudent(studentUserName);
        });
    }

    public LiveData<List<Course_Reviews>> getAllReviewsByCourseId(int courseId) {
        return courseReviewsDao.getAllReviewsByCourseId(courseId);
    }


    void insertReviewT(Teacher_Reviews review) {
        My_Database.databaseWriteExecutor.execute(() -> {
            teacherReviewsDao.insertReviewT(review);
        });
    }


    void updateReviewByStudentT(String studentUserName, String newComment, float newRating) {
        My_Database.databaseWriteExecutor.execute(() -> {
            teacherReviewsDao.updateReviewByStudentT(studentUserName, newComment, newRating);
        });
    }

    void deleteReviewByStudentT(String studentUserName) {
        My_Database.databaseWriteExecutor.execute(() -> {

            teacherReviewsDao.deleteReviewByStudentT(studentUserName);
        });
    }

    public LiveData<List<Teacher_Reviews>> getAllReviewsByCourseIdT(String teacher) {
        return teacherReviewsDao.getAllReviewsByCourseIdT(teacher);
    }

    void updateCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.updateCourse(course);


        });


    }

    void insertCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);


        });

    }


    void deleteCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);


        });

    }

    public LiveData<List<Course>> getBookmarkedCourses() {
        return courseDao.getBookmarkedCourses();
    }

    public LiveData<List<Student_Course>> getBookmarkedCoursesByStudent(String studentUsername) {
        return studentCourseDao.getBookmarkedCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getBookmarkedCoursesByStudent1(String studentUsername, int courseId) {
        return studentCourseDao.getBookmarkedCoursesByStudent1(studentUsername, courseId);

    }

    public LiveData<List<Student_Course>> getAddCartCoursesByStudent1(String studentUsername, int courseId) {
        return studentCourseDao.getisAddCartCoursesByStudent1(studentUsername, courseId);
    }

    public LiveData<List<Student_Course>> getisRatingCoursesByStudent1(String studentUsername, int courseId) {
        return studentCourseDao.getisRatingCoursesByStudent1(studentUsername, courseId);
    }

    public LiveData<List<Student_Course>> getisRegisterCoursesByStudent1(String studentUsername) {
        return studentCourseDao.getisRegisterCoursesByStudent1(studentUsername);
    }

    public LiveData<List<Student_Course>> getisAddCartCoursesByStudent(String studentUsername) {
        return studentCourseDao.getisAddCartCoursesByStudent(studentUsername);
    }

    public LiveData<List<Course>> updateBookmarkStatusAndGetCourses(int courseId, boolean isBookmarked) {
        courseDao.updateBookmarkStatus(courseId, isBookmarked);
        return courseDao.getAllCourse();
    }

    public LiveData<List<Course>> updateisAddCartStatusAndGetCourses(int courseId, boolean isAddCart) {
        courseDao.updateBookmarkStatus(courseId, isAddCart);
        return courseDao.getAllCourse();
    }


    LiveData<List<Course>> getAllCourse() {
        return courseDao.getAllCourse();
    }

    LiveData<List<Course>> getAllCoursesById(int id) {


        return courseDao.getAllCoursesById(id);
    }

    LiveData<List<Course>> getAllCoursesByTeacher_USER_Name(String Teacher_USER_Name) {


        return courseDao.getAllCoursesByTeacher_USER_Name(Teacher_USER_Name);
    }


    public LiveData<List<Course>> getCoursesByCategory(String category) {
        return courseDao.getCoursesByCategory(category);
    }

    public boolean isStudentCourseExists(String studentUsername, int courseId, boolean isRegister) {
        return studentCourseDao.isStudentCourseExists(studentUsername, courseId, isRegister) > 0;
    }

    public boolean isStudentCourseExists1(String studentUsername, int courseId, boolean isAddCart) {
        return studentCourseDao.isStudentCourseExists1(studentUsername, courseId, isAddCart) > 0;
    }

    public boolean isStudentCourseExistsB(String studentUsername, int courseId, boolean isBookmark) {
        return studentCourseDao.isStudentCourseExistsB(studentUsername, courseId, isBookmark) > 0;
    }

    public void insertStudentCourse(Student_Course studentCourse) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudentCourse(studentCourse);
        });
    } public void deleteStudentCourse(Student_Course studentCourse) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentCourseDao.deleteStudentCourse(studentCourse);
        });
    }

    public void updateCourseStudent(Student_Course studentCourse) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentCourseDao.updateCourseStudent(studentCourse);
        });
    }

    public LiveData<Void> deleteStudentCourseByUserAndCourse(String studentUsername, int courseId) {
        MutableLiveData<Void> result = new MutableLiveData<>();

        executorService.execute(() -> {
            studentCourseDao.deleteStudentCourseByUserAndCourse(studentUsername, courseId);
            result.postValue(null);
        });
        return result;

    }    public LiveData<Void> delete1StudentCourseByUserAndCourse(String studentUsername, int courseId) {
        MutableLiveData<Void> result = new MutableLiveData<>();

        executorService.execute(() -> {
            studentCourseDao.delete1StudentCourseByUserAndCourse(studentUsername, courseId);
            result.postValue(null);
        });
        return result;

    }

    public LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername) {
        return studentCourseDao.getCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getStudentsByCourse(int courseId) {
        return studentCourseDao.getStudentsByCourse(courseId);
    }

    public LiveData<List<Student_Course>> getStudentsByCourseAndStudent(String user, int courseId) {
        return studentCourseDao.getStudentsByCourseAndStudent(user, courseId);
    }

    public LiveData<List<Course>> getCoursesByIds(List<Integer> courseIds) {
        return courseDao.getCoursesByIds(courseIds);
    }

    void insertStudent(Student student) {
        My_Database.databaseWriteExecutor.execute(() -> {

            studentDao.insertStudent(student);


        });
    }

    void updateStudent(Student student) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);


        });
    }

    void deleteStudent(Student student) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);


        });
    }

    LiveData<List<Student>> getAllStudent() {

        return studentDao.getAllStudents();
    }

    LiveData<List<Student>> getStudentByUsernameAndPassword(String username, String password) {
        return studentDao.getStudentByUsernameAndPassword(username, password);
    }

    LiveData<List<Student>> getAllStudentByUser(String student_user_name) {

        return studentDao.getAllStudentsByUser(student_user_name);
    }


    void insertTeacher(Teacher teacher) {
        My_Database.databaseWriteExecutor.execute(() -> {

            teacherDao.insertTeacher(teacher);


        });
    }

    void updateTeacher(Teacher teacher) {
        My_Database.databaseWriteExecutor.execute(() -> {
            teacherDao.updateTeacher(teacher);


        });
    }

    void deleteTeacher(Teacher teacher) {
        My_Database.databaseWriteExecutor.execute(() -> {
            teacherDao.deleteTeacher(teacher);


        });
    }

    LiveData<List<Teacher>> getAllTeacher() {

        return teacherDao.getAllTeachers();
    }

    public LiveData<List<Teacher>> searchTeachers(String teacherName) {
        return teacherDao.getTeacherByName("%" + teacherName + "%");
    }

    public LiveData<List<Course>> searchCourses(String courseName) {
        return courseDao.getCourseName("%" + courseName + "%");
    }

    LiveData<List<Teacher>> getAllTeacherByUser(String Teatur_USER_Name) {

        return teacherDao.getAllTeachersByUser(Teatur_USER_Name);
    }

    public void insertStudentTeacher(Student_Teacher studentTeacher) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentTeacherDao.insertStudentTeacher(studentTeacher);
        });
    }

    public LiveData<List<Student_Teacher>> getTeachersByStudent(String studentUsername) {
        return studentTeacherDao.getTeachersByStudent(studentUsername);
    }

    public LiveData<List<Student_Teacher>> getStudentsByTeacher(String teacherUsername) {
        return studentTeacherDao.getStudentsByTeacher(teacherUsername);
    }

}
