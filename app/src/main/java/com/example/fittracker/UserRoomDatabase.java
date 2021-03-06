package com.example.fittracker;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = BuildConfig.DB_VERSION)
public abstract class UserRoomDatabase extends RoomDatabase {
    private static volatile UserRoomDatabase INSTANCE;

    public abstract UserDao userDao();

    public static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserRoomDatabase.class, ConstantUtils.DB_USER_TABLE).
                            fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
