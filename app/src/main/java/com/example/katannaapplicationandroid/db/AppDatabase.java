package com.example.katannaapplicationandroid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.katannaapplicationandroid.db.DAO.FilmDAO;
import com.example.katannaapplicationandroid.db.entity.Film;

@Database(entities = {Film.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmDAO filmDAO();
}
