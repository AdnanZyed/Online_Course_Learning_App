package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "Farmer_Seasons",
        primaryKeys = {"Farmer_user_name", "Season_ID"},
        foreignKeys = {
                @ForeignKey(entity = Farmer.class, parentColumns = "Farmer_user_name", childColumns = "Farmer_user_name", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Season.class, parentColumns = "Season_ID", childColumns = "Season_ID", onDelete = ForeignKey.CASCADE)
        }
)
public class Farmer_Seasons {


    @NonNull
    private String Farmer_user_name;
    private int Season_ID;
    private boolean isBookmark;
    private boolean isRegister;
    private boolean isAddCart;
    private int rating;

    public Farmer_Seasons(@NonNull String farmer_user_name, int season_ID, boolean isBookmark, boolean isRegister, boolean isAddCart, int rating) {
        Farmer_user_name = farmer_user_name;
        Season_ID = season_ID;
        this.isBookmark = isBookmark;
        this.isRegister = isRegister;
        this.isAddCart = isAddCart;
        this.rating = rating;
    }

    public Farmer_Seasons() {
    }

    @NonNull
    public String getFarmer_user_name() {
        return Farmer_user_name;
    }

    public void setFarmer_user_name(@NonNull String farmer_user_name) {
        Farmer_user_name = farmer_user_name;
    }

    public int getSeason_ID() {
        return Season_ID;
    }

    public void setSeason_ID(int season_ID) {
        Season_ID = season_ID;
    }

    public boolean isBookmark() {
        return isBookmark;
    }

    public void setBookmark(boolean bookmark) {
        isBookmark = bookmark;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public boolean isAddCart() {
        return isAddCart;
    }

    public void setAddCart(boolean addCart) {
        isAddCart = addCart;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Farmer_Seasons{" +
                "Farmer_user_name='" + Farmer_user_name + '\'' +
                ", Season_ID=" + Season_ID +
                ", isBookmark=" + isBookmark +
                ", isRegister=" + isRegister +
                ", isAddCart=" + isAddCart +
                ", rating=" + rating +
                '}';
    }
}
