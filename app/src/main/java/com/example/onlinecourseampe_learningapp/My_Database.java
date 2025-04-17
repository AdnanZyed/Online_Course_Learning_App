package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Farmer.class, Season.class, Expert.class, Farmer_Seasons.class, Farmer_Expert.class, Season_Reviews.class, SeasonStep.class, Message.class, Expert_Reviews.class, FarmerStep.class, Notification.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class My_Database extends RoomDatabase {
    public abstract FarmerStepDao farmerStepDao();

    public abstract NotificationDao notificationDao();

    public abstract Season_Dao seasonDao();

    public abstract MessageDao messageDao();

    public abstract Farmer_Dao farmerDao();

    public abstract Expert_Dao expertDao();

    public abstract SeasonStepsDao seasonStepsDao();


    public abstract Farmer_Season_Dao farmerSeasonDao();

    public abstract Farmer_Expert_Dao farmerExpertDao();

    public abstract SeasonReviewsDao seasonReviewsDao();

    public abstract ExpertReviewsDao expertReviewsDao();


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
