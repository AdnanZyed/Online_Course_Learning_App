package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "StudentCourse",
        primaryKeys = {"Student_user_name", "Course_ID"},
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "Student_user_name", childColumns = "Student_user_name", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Course.class, parentColumns = "Course_ID", childColumns = "Course_ID", onDelete = ForeignKey.CASCADE)
        }
)
public class Student_Course {


    @NonNull
    private String Student_user_name;
    private int Course_ID;
    private boolean isBookmark;
    private boolean isRegister;
    private boolean isAddCart;
    private int rating;

    public Student_Course(@NonNull String Student_user_name, int Course_ID, boolean isBookmark, boolean isRegister, boolean isAddCart, int rating) {
        this.Student_user_name = Student_user_name;
        this.Course_ID = Course_ID;
        this.isBookmark = isBookmark;
        this.isRegister = isRegister;
        this.isAddCart = isAddCart;
        this.rating = rating;
    }

    public Student_Course() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isBookmark() {
        return isBookmark;
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

    public void setBookmark(boolean bookmark) {
        isBookmark = bookmark;
    }

    public String getStudent_user_name() {
        return Student_user_name;
    }

    public void setStudent_user_name(String student_user_name) {
        Student_user_name = student_user_name;
    }

    public int getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(int course_ID) {
        Course_ID = course_ID;
    }

    @Override
    public String toString() {
        return "Student_Course{" +
                "Student_user_name='" + Student_user_name + '\'' +
                ", Course_ID=" + Course_ID +
                ", isBookmark=" + isBookmark +
                ", isRegister=" + isRegister +
                ", isAddCart=" + isAddCart +
                ", rating=" + rating +
                '}';
    }
}
