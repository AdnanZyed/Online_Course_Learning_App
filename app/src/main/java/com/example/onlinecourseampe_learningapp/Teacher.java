package com.example.onlinecourseampe_learningapp;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;


//@TypeConverters(Converter.class)
//@Entity(tableName = "Teacher")
@Entity(foreignKeys = @ForeignKey(entity = Course.class, parentColumns = {"Course_ID"}, childColumns = {"Teatur_USER_Name"}))
public class Teacher {
    @PrimaryKey
    @NonNull
    private String Teatur_USER_Name;
    private String Teatur_name;
    private String Teatur_PASSWORD;
    private byte[] Image_teatcher;
    private int Course_ID;

    public Teacher(@NonNull String teatur_USER_Name, String teatur_name, String teatur_PASSWORD, byte[] image_teatcher, int course_ID) {
        Teatur_USER_Name = teatur_USER_Name;
        Teatur_name = teatur_name;
        Teatur_PASSWORD = teatur_PASSWORD;
        Image_teatcher = image_teatcher;
        Course_ID = course_ID;
    }

    public Teacher() {
    }

    @NonNull
    public String getTeatur_USER_Name() {
        return Teatur_USER_Name;
    }

    public void setTeatur_USER_Name(@NonNull String teatur_USER_Name) {
        Teatur_USER_Name = teatur_USER_Name;
    }

    public String getTeatur_name() {
        return Teatur_name;
    }

    public void setTeatur_name(String teatur_name) {
        Teatur_name = teatur_name;
    }

    public String getTeatur_PASSWORD() {
        return Teatur_PASSWORD;
    }

    public void setTeatur_PASSWORD(String teatur_PASSWORD) {
        Teatur_PASSWORD = teatur_PASSWORD;
    }

    public byte[] getImage_teatcher() {
        return Image_teatcher;
    }

    public void setImage_teatcher(byte[] image_teatcher) {
        Image_teatcher = image_teatcher;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "Teatur_USER_Name='" + Teatur_USER_Name + '\'' +
                ", Teatur_name='" + Teatur_name + '\'' +
                ", Teatur_PASSWORD='" + Teatur_PASSWORD + '\'' +
                ", Image_teatcher=" + Arrays.toString(Image_teatcher) +
                ", Course_ID=" + Course_ID +
                '}';
    }
}

