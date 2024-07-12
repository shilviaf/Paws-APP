package com.example.pawapps.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pawapps.database.dao.DatabaseDao2;
import com.example.pawapps.model.ModelDatabase2;

@Database(entities = {ModelDatabase2.class}, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {
    public abstract DatabaseDao2 databaseDao2();
}
