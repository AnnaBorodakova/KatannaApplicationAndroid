package com.example.katannaapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katannaapplicationandroid.Entity.Film;

public class FilmDetailsActivity extends AppCompatActivity {

    String[] genres = {"Комедия", "Драма", "Триллер", "Боевик"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_film_details);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {
            Film film = (Film) arguments.get("film");
            TextView title = findViewById(R.id.name_textbox);
            CheckBox isfavoriteCheckBox = findViewById(R.id.is_favourite);
            title.setText(film.getName());
           // title.setFocusedByDefault(false);
            isfavoriteCheckBox.setChecked(film.isFavorite());
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
      CharSequence text = name.getText();
      //не работает проверка
      if (name.getText().toString()=="")
          Toast.makeText(this, "Введите название фильма", Toast.LENGTH_SHORT).show();
      else Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_SHORT).show();
    }
}