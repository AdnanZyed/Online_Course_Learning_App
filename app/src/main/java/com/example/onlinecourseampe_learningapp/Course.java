package com.example.onlinecourseampe_learningapp;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Arrays;


//@Entity(tableName = "Course")
@Entity(tableName = "Course", foreignKeys = @ForeignKey(
        entity = Teacher.class,
        parentColumns = "Teatur_USER_Name",
        childColumns = "Teacher_USER_Name",
        onDelete = ForeignKey.CASCADE
))

public class Course {
    @PrimaryKey(autoGenerate = true)
    private int Course_ID;
    //هذه البيانات يتم ادخالها من حساب الادمن بحيث يستطيع ايضا عندا يقوم بالتسجيل كادمن ان يحذف كورسات او يضيف او يعدل من خلال الاي دي كما يتم عرض جميع الكورسات للطلاب او مجموعة معينة من الكورسات بناءا على التصنيف وهنا كل كورس يدرسه مدرس معين يعني اريد ربط الكورس بمدرس بناءا على انه كل كورس له تصنيف معين مثلا ديزاين برمجة وكل مدرس له تصنيف معين ايضا
    private String Course_NAME;//اسم الكورس

    private byte[] Image; //صورة شهادة الكورس
    private int Price;//سعر الكورس
    private String Categorie; //الكورس ينضم لاي تصنيف عند العرض
    private String Description;//وصف الكورس
    private String Teacher_name; //اسم مدرس الكورس
    private byte[] profilePicture; //صورة الكورس
    private boolean isBookmarked; // متغير الإشارة المرجعية
    private String Teacher_USER_Name;  // الإشارة إلى المدرس عن طريق اسم المستخدم


    //private String Student_user_name;


    public Course(int course_ID, String course_NAME, byte[] image, int price, String categorie, String description, String teacher_name, byte[] profilePicture, boolean isBookmarked, String teacher_USER_Name) {
        Course_ID = course_ID;
        Course_NAME = course_NAME;
        Image = image;
        Price = price;
        Categorie = categorie;
        Description = description;
        Teacher_name = teacher_name;
        this.profilePicture = profilePicture;
        this.isBookmarked = isBookmarked;
        Teacher_USER_Name = teacher_USER_Name;
    }

    public Course() {
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

    public void setCourse_NAME( String course_NAME) {
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

    // Getters and setters
    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

//    public String getStudent_user_name() {
//        return Student_user_name;
//    }
//
//    public void setStudent_user_name(String student_user_name) {
//        Student_user_name = student_user_name;
//    }


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
                ", Teacher_USER_Name='" + Teacher_USER_Name + '\'' +
                '}';
    }
}

