package com.example.katannaapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katannaapplicationandroid.db.entity.Film;

import java.util.List;

public class FilmDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_film_details);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            Film film = (Film) arguments.get("film");
            TextView title = findViewById(R.id.name_textbox);
            CheckBox status = findViewById(R.id.is_watched);
            CheckBox favorite = findViewById(R.id.is_favourite);
            EditText comment = findViewById(R.id.comment_textbox);
            title.setText(film.getFilmName());
            comment.setText(film.getComment());
            status.setChecked(film.isStatus());
            favorite.setChecked(film.isFavorite());
            findViewById(R.id.delete_button).setVisibility(View.VISIBLE);
        }

        Spinner spinner = (Spinner) findViewById(R.id.genre_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genres, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void onBackButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSaveButtonClick(View view) {
        TextView name = findViewById(R.id.name_textbox);
        Spinner genre = findViewById(R.id.genre_spinner);
        CheckBox status = findViewById(R.id.is_watched);
        CheckBox favorite = findViewById(R.id.is_favourite);
        EditText comment = findViewById(R.id.comment_textbox);
        Bundle arguments = getIntent().getExtras();
        if (!name.getText().toString().equals("")) {
            if (arguments != null) {
                Film film = (Film) arguments.get("film");
                updateFilm(view, film, name.getText().toString(), genre.getSelectedItem().toString(), status.isChecked(),
                        favorite.isChecked(), comment.getText().toString());
            }else addFilm(view, name.getText().toString(), genre.getSelectedItem().toString(), status.isChecked(),
                    favorite.isChecked(), comment.getText().toString());
        } else Toast.makeText(this, "Введите название фильма", Toast.LENGTH_SHORT).show();
    }

    public void onDeleteButtonClick(View view){
        Bundle arguments = getIntent().getExtras();
        Film film = (Film) arguments.get("film");
        MainActivity.getDatabase().filmDAO().delete(film);
        onBackButtonClick(view);
    }

    public void updateFilm(View view, Film film, String name, String genre, boolean status, boolean favorite, String comment) {
        if ((!MainActivity.getDatabase().filmDAO().findFilm(name).isEmpty() && film.getFilmName().equals(name)) ||
                MainActivity.getDatabase().filmDAO().findFilm(name).isEmpty()) {
            film.setComment(comment);
            film.setFavorite(favorite);
            film.setFilmName(name);
            film.setGenre(genre);
            film.setStatus(status);
            MainActivity.getDatabase().filmDAO().update(film);
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
            onBackButtonClick(view);
        } else
            Toast.makeText(this, "Фильм с таким названием уже существует", Toast.LENGTH_SHORT).show();
    }

    public void addFilm(View view, String name, String genre, boolean status, boolean favorite, String comment) {
        if (MainActivity.getDatabase().filmDAO().findFilm(name).isEmpty()) {
            MainActivity.getDatabase().filmDAO().insert(new Film(name, status, favorite,
                    comment, genre));
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
            onBackButtonClick(view);
        } else
            Toast.makeText(this, "Фильм с таким названием уже существует", Toast.LENGTH_SHORT).show();
    }
}