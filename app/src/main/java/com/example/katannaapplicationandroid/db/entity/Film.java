package com.example.katannaapplicationandroid.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
public class Film implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int filmId;
    @ColumnInfo(name = "filmName")
    private String filmName;
    @ColumnInfo(name = "status")
    private boolean status;
    @ColumnInfo(name = "favorite")
    private boolean favorite;
    @ColumnInfo(name = "comment")
    private String comment;
    @ColumnInfo(name = "genre")
    private String genre;

    public Film() {
    }

    public Film(String filmName, boolean status, String genre) {
        this.filmName = filmName;
        this.status = status;
        this.genre = genre;
    }

    public Film(String filmName, boolean status, boolean favorite, String comment, String genre) {
        this.filmName = filmName;
        this.status = status;
        this.favorite = favorite;
        this.comment = comment;
        this.genre = genre;
    }

    protected Film(Parcel in) {
        filmId = in.readInt();
        filmName = in.readString();
        status = in.readByte() != 0;
        favorite = in.readByte() != 0;
        comment = in.readString();
        genre = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(filmId);
        parcel.writeString(filmName);
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeString(comment);
        parcel.writeString(genre);
    }
}
