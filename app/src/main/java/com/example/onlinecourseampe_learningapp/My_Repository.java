package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class My_Repository {


private Student_Course_Dao studentCourseDao;
private Student_Teacher_Dao studentTeacherDao;
    private Teacher_Dao teacherDao;
    private Student_Dao studentDao;
    private Course_Dao courseDao;

    private LiveData<List<Teacher>> AllTeacher;
    private LiveData<List<Student>> AllStudents;
    private LiveData<List<Course>> AllCourse;


    My_Repository(Application application) {
        My_Database db = My_Database.getDatabase(application);
        studentDao = db.studentDao();
        courseDao = db.courseDao();
        teacherDao = db.teacherDao();

        //mAllWords = mWordDao.getAlphabetizedWords();
    }

    void insertCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);


        });


    }

    void updateCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.updateCourse(course);


        });


    }

    void deleteCourse(Course course) {
        My_Database.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);


        });

    }

    // دالة لجلب الكورسات المفضلة
    public LiveData<List<Course>> getBookmarkedCourses() {
        return courseDao.getBookmarkedCourses();  // ترجع LiveData بدلاً من List
    }

    public LiveData<List<Course>> updateBookmarkStatusAndGetCourses(int courseId, boolean isBookmarked) {
        courseDao.updateBookmarkStatus(courseId, isBookmarked);  // تنفيذ التحديث أولاً
        return courseDao.getAllCourse();  // جلب الكورسات بعد التحديث
    }


    LiveData<List<Course>> getAllCourse() {
        return courseDao.getAllCourse();
    }
    // دالة لحذف جميع الكورسات

    LiveData<List<Course>> getAllCoursesById(int id) {


        return courseDao.getAllCoursesById(id);
    }

    public void insertStudentCourse(Student_Course studentCourse) {
        My_Database.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudentCourse(studentCourse);
        });
    }

    public LiveData<List<Student_Course>> getCoursesByStudent(String studentUsername) {
        return studentCourseDao.getCoursesByStudent(studentUsername);
    }

    public LiveData<List<Student_Course>> getStudentsByCourse(int courseId) {
        return studentCourseDao.getStudentsByCourse(courseId);
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

    // دالة للبحث عن المعلمين
    public LiveData<List<Teacher>> searchTeachers(String teacherName) {
        return teacherDao.getTeacherByName("%" + teacherName + "%");
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
