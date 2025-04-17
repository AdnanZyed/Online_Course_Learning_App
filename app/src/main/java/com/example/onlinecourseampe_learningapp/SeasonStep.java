package com.example.onlinecourseampe_learningapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "SeasonSteps", foreignKeys = @ForeignKey(
        entity = Season.class,
        parentColumns = "Season_ID",
        childColumns = "Season_ID",

        onDelete = ForeignKey.CASCADE

)

)
public class SeasonStep {

    @PrimaryKey(autoGenerate = true)
    private int s_id;
    private String s_name;
    private String s_url;
    private int s_time;
    private boolean s_completed;
    private int Season_ID;

    public SeasonStep() {
    }

    public SeasonStep(int s_id, String s_name, String s_url, int s_time, boolean s_completed, int season_ID) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.s_url = s_url;
        this.s_time = s_time;
        this.s_completed = s_completed;
        Season_ID = season_ID;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_url() {
        return s_url;
    }

    public void setS_url(String s_url) {
        this.s_url = s_url;
    }

    public int getS_time() {
        return s_time;
    }

    public void setS_time(int s_time) {
        this.s_time = s_time;
    }

    public boolean isS_completed() {
        return s_completed;
    }

    public void setS_completed(boolean s_completed) {
        this.s_completed = s_completed;
    }

    public int getSeason_ID() {
        return Season_ID;
    }

    public void setSeason_ID(int season_ID) {
        Season_ID = season_ID;
    }

    @Override
    public String toString() {
        return "SeasonStep{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_url='" + s_url + '\'' +
                ", s_time=" + s_time +
                ", s_completed=" + s_completed +
                ", Season_ID=" + Season_ID +
                '}';
    }
}