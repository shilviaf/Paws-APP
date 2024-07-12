package com.example.pawapps.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient2 {

    private static DatabaseClient2 mInstance;
    private AppDatabase2 mAppDatabase2;

    private DatabaseClient2(Context context){
        mAppDatabase2 = Room.databaseBuilder(context, AppDatabase2.class, "stock_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DatabaseClient2 getInstance(Context context){
        if (mInstance == null){
            mInstance = new DatabaseClient2(context);
        }
        return mInstance;
    }

    public static DatabaseClient2 getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        throw new IllegalArgumentException("Should use getInstance(Context) " +
                "at least once before using this method.");
    }

    public AppDatabase2 getAppDatabase2(){
        return mAppDatabase2;
    }
}
