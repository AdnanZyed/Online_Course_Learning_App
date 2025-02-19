package com.example.onlinecourseampe_learningapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class, Teacher.class, Student_Course.class, Student_Teacher.class, Course_Reviews.class, CourseLessons.class, Message.class, Teacher_Reviews.class, StudentLesson.class, Notification.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class My_Database extends RoomDatabase {
    public abstract StudentLessonDao studentLessonDao();

    public abstract NotificationDao notificationDao();

    public abstract Course_Dao courseDao();

    public abstract MessageDao messageDao();

    public abstract Student_Dao studentDao();

    public abstract Teacher_Dao teacherDao();

    public abstract CourseLessonsDao courseLessonsDao();


    public abstract Student_Course_Dao studentCourseDao();

    public abstract Student_Teacher_Dao studentTeacherDao();

    public abstract CourseReviewsDao courseReviewsDao();

    public abstract TeacherReviewsDao teacherReviewsDao();


    private static volatile My_Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);


    static My_Database getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (My_Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    My_Database.class, "My_Database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
