package com.example.recyclerview;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "contact-db"
            ).build();
        }
        return INSTANCE;
    }
}

