package com.sushma.imagesearch.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Image.class, Comment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String databaseName = "ImageDB";
    private static AppDatabase instance;

    public abstract ImageDao imageDao();

    public abstract CommentDao commentDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, databaseName).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}

