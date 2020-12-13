package com.example.katannaapplicationandroid.db.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.katannaapplicationandroid.db.entity.Film;

import java.util.List;
@Dao
public interface FilmDAO {
    @Insert
    void insert(Film film);

    @Insert
    void insertAll(List<Film> films);

    @Update
    void update(Film film);

    @Delete
    void delete(Film film);

    @Query("SELECT * FROM film")
    List<Film> getAll();

    @Query("SELECT * FROM film where filmName = :name")
    List<Film> findFilm(String name);
}
