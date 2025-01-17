package com.example.onlinecourseampe_learningapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.net.URL;
@Entity(tableName = "CourseLessons", foreignKeys = @ForeignKey(
        entity = Course.class,
        parentColumns = "Course_ID",
        childColumns = "Course_ID",
        onDelete = ForeignKey.CASCADE
))
public class CourseLessons {

    @PrimaryKey(autoGenerate = true)
    private int l_id;
    private String l_name;
    private String l_url;
    private int l_time;
    private boolean l_completed;
    private int Course_ID;

    public CourseLessons() {
    }

    public CourseLessons(int l_id, String l_name, String l_url, int l_time, boolean l_completed, int course_ID) {
        this.l_id = l_id;
        this.l_name = l_name;
        this.l_url = l_url;
        this.l_time = l_time;
        this.l_completed = l_completed;
        Course_ID = course_ID;
    }

    public int getL_id() {
        return l_id;
    }

    public void setL_id(int l_id) {
        this.l_id = l_id;
    }


    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getL_url() {
        return l_url;
    }

    public void setL_url(String l_url) {
        this.l_url = l_url;
    }

    public int getL_time() {
        return l_time;
    }

    public void setL_time(int l_time) {
        this.l_time = l_time;
    }

    public boolean isL_completed() {
        return l_completed;
    }

    public void setL_completed(boolean l_completed) {
        this.l_completed = l_completed;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    @Override
    public String toString() {
        return "CourseLessons{" +
                "l_id=" + l_id +
                ", l_name=" + l_name +
                ", l_url='" + l_url + '\'' +
                ", l_time=" + l_time +
                ", l_completed=" + l_completed +
                ", Course_ID=" + Course_ID +
                '}';
    }
}

