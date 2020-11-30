package com.example.katannaapplicationandroid.Entity;

import java.io.Serializable;

public class Film implements Serializable {

    private String name;
    private boolean isFavorite;
    private String genre;

    public Film(String name, boolean isFavorite, String genre) {
        this.name = name;
        this.isFavorite = isFavorite;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
