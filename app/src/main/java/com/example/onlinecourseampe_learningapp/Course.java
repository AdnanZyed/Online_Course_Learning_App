package com.example.onlinecourseampe_learningapp;

import android.media.Rating;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "Course", foreignKeys = @ForeignKey(
        entity = Teacher.class,
        parentColumns = "Teatur_USER_Name",
        childColumns = "Teacher_USER_Name",
        onDelete = ForeignKey.CASCADE
))

public class Course {
    @PrimaryKey(autoGenerate = true)
    private int Course_ID;
    private String Course_NAME;

    private byte[] Image;
    private int Price;
    private String Categorie;
    private String Description;
    private String Teacher_name;
    private byte[] profilePicture;
    private boolean isBookmarked;
    private boolean isAddCart;
    private boolean isCompleted;
    private String Teacher_USER_Name;
    private byte[] bookmarkIcon;
    private double rating;
    private int reviews;


    public Course(int course_ID, String course_NAME, byte[] image, int price, String categorie, String description, String teacher_name, byte[] profilePicture, boolean isBookmarked, boolean isAddCart, boolean isCompleted, String teacher_USER_Name, byte[] bookmarkIcon, double rating, int reviews) {
        Course_ID = course_ID;
        Course_NAME = course_NAME;
        Image = image;
        Price = price;
        Categorie = categorie;
        Description = description;
        Teacher_name = teacher_name;
        this.profilePicture = profilePicture;
        this.isBookmarked = isBookmarked;
        this.isAddCart = isAddCart;
        this.isCompleted = isCompleted;
        Teacher_USER_Name = teacher_USER_Name;
        this.bookmarkIcon = bookmarkIcon;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Course() {
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

    public byte[] getBookmarkIcon() {
        return bookmarkIcon;
    }

    public void setBookmarkIcon(byte[] bookmarkIcon) {
        this.bookmarkIcon = bookmarkIcon;
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

    public String getTeacher_USER_Name() {
        return Teacher_USER_Name;
    }

    public void setTeacher_USER_Name(String teacher_USER_Name) {
        Teacher_USER_Name = teacher_USER_Name;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    public String getCourse_NAME() {
        return Course_NAME;
    }

    public void setCourse_NAME(String course_NAME) {
        Course_NAME = course_NAME;
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

    public String getTeacher_name() {
        return Teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        Teacher_name = teacher_name;
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


    @Override
    public String toString() {
        return "Course{" +
                "Course_ID=" + Course_ID +
                ", Course_NAME='" + Course_NAME + '\'' +
                ", Image=" + Arrays.toString(Image) +
                ", Price=" + Price +
                ", Categorie='" + Categorie + '\'' +
                ", Description='" + Description + '\'' +
                ", Teacher_name='" + Teacher_name + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", isBookmarked=" + isBookmarked +
                ", isAddCart=" + isAddCart +
                ", isCompleted=" + isCompleted +
                ", Teacher_USER_Name='" + Teacher_USER_Name + '\'' +
                ", bookmarkIcon=" + Arrays.toString(bookmarkIcon) +
                ", rating=" + rating +
                ", reviews=" + reviews +
                '}';
    }
}

