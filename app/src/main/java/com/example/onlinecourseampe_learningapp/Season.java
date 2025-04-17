package com.example.onlinecourseampe_learningapp;

import android.media.Rating;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "Season", foreignKeys = @ForeignKey(
        entity = Expert.class,
        parentColumns = "Teatur_USER_Name",
        childColumns = "Expert_USER_Name",
        onDelete = ForeignKey.CASCADE
))

public class Season {
    @PrimaryKey(autoGenerate = true)
    private int Season_ID;
    private String Season_NAME;

    private byte[] Image;
    private int Price;
    private String Categorie;
    private String Description;
    private String Expert_name;
    private byte[] profilePicture;
    private boolean isBookmarked;
    private boolean isAddCart;
    private boolean isCompleted;
    private String Expert_USER_Name;
    private byte[] bookmarkIcon;
    private double rating;
    private int reviews;


    public Season(int season_ID, String season_NAME, byte[] image, int price, String categorie, String description, String expert_name, byte[] profilePicture, boolean isBookmarked, boolean isAddCart, boolean isCompleted, String expert_USER_Name, byte[] bookmarkIcon, double rating, int reviews) {
        Season_ID = season_ID;
        Season_NAME = season_NAME;
        Image = image;
        Price = price;
        Categorie = categorie;
        Description = description;
        Expert_name = expert_name;
        this.profilePicture = profilePicture;
        this.isBookmarked = isBookmarked;
        this.isAddCart = isAddCart;
        this.isCompleted = isCompleted;
        Expert_USER_Name = expert_USER_Name;
        this.bookmarkIcon = bookmarkIcon;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Season() {
    }

    public int getSeason_ID() {
        return Season_ID;
    }

    public void setSeason_ID(int season_ID) {
        Season_ID = season_ID;
    }

    public String getSeason_NAME() {
        return Season_NAME;
    }

    public void setSeason_NAME(String season_NAME) {
        Season_NAME = season_NAME;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getExpert_name() {
        return Expert_name;
    }

    public void setExpert_name(String expert_name) {
        Expert_name = expert_name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public boolean isAddCart() {
        return isAddCart;
    }

    public void setAddCart(boolean addCart) {
        isAddCart = addCart;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getExpert_USER_Name() {
        return Expert_USER_Name;
    }

    public void setExpert_USER_Name(String expert_USER_Name) {
        Expert_USER_Name = expert_USER_Name;
    }

    public byte[] getBookmarkIcon() {
        return bookmarkIcon;
    }

    public void setBookmarkIcon(byte[] bookmarkIcon) {
        this.bookmarkIcon = bookmarkIcon;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Season{" +
                "Season_ID=" + Season_ID +
                ", Season_NAME='" + Season_NAME + '\'' +
                ", Image=" + Arrays.toString(Image) +
                ", Price=" + Price +
                ", Categorie='" + Categorie + '\'' +
                ", Description='" + Description + '\'' +
                ", Expert_name='" + Expert_name + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", isBookmarked=" + isBookmarked +
                ", isAddCart=" + isAddCart +
                ", isCompleted=" + isCompleted +
                ", Expert_USER_Name='" + Expert_USER_Name + '\'' +
                ", bookmarkIcon=" + Arrays.toString(bookmarkIcon) +
                ", rating=" + rating +
                ", reviews=" + reviews +
                '}';
    }
}

