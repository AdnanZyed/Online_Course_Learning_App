package com.example.onlinecourseampe_learningapp;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

@Entity(tableName = "Student")
public class Student {

    @PrimaryKey
    @NonNull
    private String Student_user_name;
    private String Student_Password;
    private int phone_nomber;
    private int Card_Number;
    private String S_name;
    private byte[] s_Image;
    private String bio;

    public Student(@NonNull String student_user_name, String student_Password, int phone_nomber, int card_Number, String s_name, byte[] s_Image, String bio) {
        Student_user_name = student_user_name;
        Student_Password = student_Password;
        this.phone_nomber = phone_nomber;
        Card_Number = card_Number;
        S_name = s_name;
        this.s_Image = s_Image;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public int getCard_Number() {
        return Card_Number;
    }

    public void setCard_Number(int card_Number) {
        Card_Number = card_Number;
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

    @Override
    public String toString() {
        return "Student{" +
                "Student_user_name='" + Student_user_name + '\'' +
                ", Student_Password='" + Student_Password + '\'' +
                ", phone_nomber=" + phone_nomber +
                ", Card_Number=" + Card_Number +
                ", S_name='" + S_name + '\'' +
                ", s_Image=" + Arrays.toString(s_Image) +
                ", bio='" + bio + '\'' +
                '}';
    }


}