package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "StudentLesson",
        primaryKeys = {"studentUserName", "lessonId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Student.class,
                        parentColumns = "Student_user_name",
                        childColumns = "studentUserName",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = CourseLessons.class,
                        parentColumns = "l_id",
                        childColumns = "lessonId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class StudentLesson {
    @NonNull
    private String studentUserName;
    private int lessonId;
    private boolean completed;

    public StudentLesson(@NonNull String studentUserName, int lessonId, boolean completed) {
        this.studentUserName = studentUserName;
        this.lessonId = lessonId;
        this.completed = completed;
    }


    @NonNull
    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(@NonNull String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
