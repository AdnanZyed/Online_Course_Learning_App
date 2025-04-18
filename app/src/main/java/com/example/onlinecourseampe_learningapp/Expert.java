package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "Expert")
public class Expert {

    @PrimaryKey
    @NonNull
    private String Teatur_USER_Name;
    private String Teatur_name;
    private String Education;
    private String Teatur_PASSWORD;
    private byte[] Image_teatcher;


    public Expert(@NonNull String teatur_USER_Name, String teatur_name, String education, String teatur_PASSWORD, byte[] image_teatcher) {
        Teatur_USER_Name = teatur_USER_Name;
        Teatur_name = teatur_name;
        Education = education;
        Teatur_PASSWORD = teatur_PASSWORD;
        Image_teatcher = image_teatcher;
    }

    public Expert() {
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

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
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


    @Override
    public String toString() {
        return "Expert{" +
                "Teatur_USER_Name='" + Teatur_USER_Name + '\'' +
                ", Teatur_name='" + Teatur_name + '\'' +
                ", Education='" + Education + '\'' +
                ", Teatur_PASSWORD='" + Teatur_PASSWORD + '\'' +
                ", Image_teatcher=" + Arrays.toString(Image_teatcher) +
                '}';
    }
}

