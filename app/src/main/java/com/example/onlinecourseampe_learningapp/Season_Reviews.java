package com.example.onlinecourseampe_learningapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "Season_Reviews", foreignKeys = @ForeignKey(
        entity = Season.class,
        parentColumns = "Season_ID",
        childColumns = "Season_ID_R",
        onDelete = ForeignKey.CASCADE
))
public class Season_Reviews {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private byte[] image;
    private String name;
    private String s_u_name;
    private String comment;
    private String formattedDate;

    private int love;

    private int Season_ID_R;
    private int rating;
    private boolean isChecked;

    public Season_Reviews(int id, byte[] image, String name, String s_u_name, String comment, String formattedDate, int love, int season_ID_R, int rating, boolean isChecked) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.s_u_name = s_u_name;
        this.comment = comment;
        this.formattedDate = formattedDate;
        this.love = love;
        Season_ID_R = season_ID_R;
        this.rating = rating;
        this.isChecked = isChecked;
    }

    public Season_Reviews() {
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

    public int getSeason_ID_R() {
        return Season_ID_R;
    }

    public void setSeason_ID_R(int season_ID_R) {
        Season_ID_R = season_ID_R;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Season_Reviews{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", name='" + name + '\'' +
                ", s_u_name='" + s_u_name + '\'' +
                ", comment='" + comment + '\'' +
                ", formattedDate='" + formattedDate + '\'' +
                ", love=" + love +
                ", Season_ID_R=" + Season_ID_R +
                ", rating=" + rating +
                ", isChecked=" + isChecked +
                '}';
    }
}
