package com.example.onlinecourseampe_learningapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;


@Entity(tableName = "Teacher_Reviews", foreignKeys = @ForeignKey(
        entity = Teacher.class,
        parentColumns = "Teatur_USER_Name",
        childColumns = "Teatur_USER_Name",
        onDelete = ForeignKey.CASCADE
))
public class Teacher_Reviews {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private byte[] image;
    private String name;
    private String s_u_name;
    private String comment;
    private String formattedDate;
    private int love;
    private String Teatur_USER_Name;
    private int rating;
    private boolean isChecked;


    public Teacher_Reviews(int id, byte[] image, String name, String s_u_name, String comment, String formattedDate, int love, String teatur_USER_Name, int rating, boolean isChecked) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.s_u_name = s_u_name;
        this.comment = comment;
        this.formattedDate = formattedDate;
        this.love = love;
        Teatur_USER_Name = teatur_USER_Name;
        this.rating = rating;
        this.isChecked = isChecked;
    }

    public Teacher_Reviews() {
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS_u_name() {
        return s_u_name;
    }

    public void setS_u_name(String s_u_name) {
        this.s_u_name = s_u_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public String getTeatur_USER_Name() {
        return Teatur_USER_Name;
    }

    public void setTeatur_USER_Name(String teatur_USER_Name) {
        Teatur_USER_Name = teatur_USER_Name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Teacher_Reviews{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", name='" + name + '\'' +
                ", s_u_name='" + s_u_name + '\'' +
                ", comment='" + comment + '\'' +
                ", formattedDate='" + formattedDate + '\'' +
                ", love=" + love +
                ", Teatur_USER_Name='" + Teatur_USER_Name + '\'' +
                ", rating=" + rating +
                ", isChecked=" + isChecked +
                '}';
    }
}
