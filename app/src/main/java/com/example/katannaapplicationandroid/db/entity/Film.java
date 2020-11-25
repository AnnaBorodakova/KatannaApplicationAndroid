package com.example.katannaapplicationandroid.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
public class Film {
    @PrimaryKey(autoGenerate = true)
    private int filmId;
    @ColumnInfo(name = "filmName")
    private String filmName;
    @ColumnInfo(name = "status")
    private boolean status;
    @ColumnInfo(name = "favorites")
    private boolean favorites;
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "genre")
    private String genre;

    public Film() {
    }

    public Film(String filmName, boolean status, boolean favorites, String comment, String genre) {
        this.filmName = filmName;
        this.status = status;
        this.favorites = favorites;
        this.comment = comment;
        this.genre = genre;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
