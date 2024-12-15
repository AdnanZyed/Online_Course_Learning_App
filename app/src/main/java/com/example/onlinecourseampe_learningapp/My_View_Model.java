package com.example.onlinecourseampe_learningapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class My_View_Model extends AndroidViewModel {

    private My_Repository repository;

    public My_View_Model(@NonNull Application application) {
        super(application);
        repository = new My_Repository(application);

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

    LiveData<List<Course>> getAllCoursesById(int id) {


        return repository.getAllCoursesById(id);
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

    LiveData<List<Teacher>> getAllTeacherByUser(String Teatur_USER_Name) {

        return repository.getAllTeacherByUser(Teatur_USER_Name);
    }


}