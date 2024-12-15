package com.example.onlinecourseampe_learningapp;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
//@Entity(tableName = "Student")

@Entity(foreignKeys = @ForeignKey(entity = Course.class, parentColumns = {"Course_ID"}, childColumns = {"Student_user_name"}))
public class Student {
    @PrimaryKey
    @NonNull
    private String Student_user_name;
    private String Student_Password;
    private int phone_nomber;
    private String S_name;
    private byte[] s_Image;
    private String S_Interest;
    private int Course_ID;


    public Student(@NonNull String student_user_name, String student_Password, int phone_nomber, String s_name, byte[] s_Image, String s_Interest, int course_ID) {
        Student_user_name = student_user_name;
        Student_Password = student_Password;
        this.phone_nomber = phone_nomber;
        S_name = s_name;
        this.s_Image = s_Image;
        S_Interest = s_Interest;
        Course_ID = course_ID;
    }

    public Student() {
    }

    @NonNull
    public String getStudent_user_name() {
        return Student_user_name;
    }

    public void setStudent_user_name(@NonNull String student_user_name) {
        Student_user_name = student_user_name;
    }

    public String getStudent_Password() {
        return Student_Password;
    }

    public void setStudent_Password(String student_Password) {
        Student_Password = student_Password;
    }

    public int getPhone_nomber() {
        return phone_nomber;
    }

    public void setPhone_nomber(int phone_nomber) {
        this.phone_nomber = phone_nomber;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

    public byte[] getS_Image() {
        return s_Image;
    }

    public void setS_Image(byte[] s_Image) {
        this.s_Image = s_Image;
    }

    public String getS_Interest() {
        return S_Interest;
    }

    public void setS_Interest(String s_Interest) {
        S_Interest = s_Interest;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Student_user_name='" + Student_user_name + '\'' +
                ", Student_Password='" + Student_Password + '\'' +
                ", phone_nomber=" + phone_nomber +
                ", S_name='" + S_name + '\'' +
                ", s_Image=" + Arrays.toString(s_Image) +
                ", S_Interest='" + S_Interest + '\'' +
                ", Course_ID=" + Course_ID +
                '}';
    }
}