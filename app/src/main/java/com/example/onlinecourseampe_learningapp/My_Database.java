package com.example.onlinecourseampe_learningapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class,Teacher.class}, version = 1, exportSchema = false)

public abstract class My_Database extends RoomDatabase {

    public abstract Course_Dao courseDao();

    public abstract Student_Dao studentDao();
    public abstract Teacher_Dao teacherDao();

    private static volatile My_Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
