package com.example.pawapps.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pawapps.database.dao.DatabaseDao;
import com.example.pawapps.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
