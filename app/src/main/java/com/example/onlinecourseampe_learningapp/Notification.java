package com.example.onlinecourseampe_learningapp;

public class Notification {
    private String title;
    private String message;
    private int iconResId;

    public Notification(String title, String message, int iconResId) {
        this.title = title;
        this.message = message;
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getIconResId() {
        return iconResId;
    }
}
