package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class My_Repository {



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

    LiveData<List<Course>> getAllCourse() {
        return courseDao.getAllCourse();
    }

    LiveData<List<Course>> getAllCoursesById(int id) {


        return courseDao.getAllCoursesById(id);
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

    LiveData<List<Teacher>> getAllTeacherByUser(String Teatur_USER_Name) {

        return teacherDao.getAllTeachersByUser(Teatur_USER_Name);
    }


}
