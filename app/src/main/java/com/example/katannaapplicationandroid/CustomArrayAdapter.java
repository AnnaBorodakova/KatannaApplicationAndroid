package com.example.katannaapplicationandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.katannaapplicationandroid.Entity.Film;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private int layout;
    private List<String> genres;
    private List<String> selectedGenres;

    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull List<String> genres, List<String> selectedGenres) {
        super(context, resource, genres);
        this.genres = genres;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.selectedGenres = selectedGenres;
    }

    public View getView(int position, View view, ViewGroup parent) {
        CustomArrayAdapter.ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(this.layout, parent, false);
            viewHolder = new CustomArrayAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CustomArrayAdapter.ViewHolder) view.getTag();
        }

        String genre = genres.get(position);
        viewHolder.checkedTextView.setText(genre);
        if (selectedGenres.contains(genre))
            viewHolder.checkedTextView.setChecked(true);
        return view;
    }


    private class ViewHolder {
       // final TextView textView;
        //final CheckBox checkBox;
       final CheckedTextView checkedTextView;

        ViewHolder(View view) {
           // textView = (TextView) view.findViewById(R.id.item_genre);
            //checkBox = (CheckBox) view.findViewById(R.id.genre_checkbox);
            checkedTextView = (CheckedTextView) view.findViewById(R.id.checked_text_view);

        }
    }
}
