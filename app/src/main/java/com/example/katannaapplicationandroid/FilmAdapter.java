package com.example.katannaapplicationandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.katannaapplicationandroid.Entity.Film;

import java.util.List;

public class FilmAdapter extends ArrayAdapter<Film> {

    private LayoutInflater inflater;
    private int layout;
    private List<Film> films;

    public FilmAdapter(@NonNull Context context, int resource, @NonNull List<Film> films) {
        super(context, resource, films);
        this.films = films;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if(view==null){
            view = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Film film = films.get(position);
        viewHolder.name.setText(film.getName());
        viewHolder.favorite.setVisibility(film.isFavorite() ? View.VISIBLE : View.INVISIBLE);
        return view;
    }

    public void update() {
        notifyDataSetChanged();
    }



    private class ViewHolder {
        final TextView name;
        final ImageView favorite;
        ViewHolder(View view){
            name = (TextView) view.findViewById(R.id.item_title);
            favorite = (ImageView)view.findViewById(R.id.item_fav);

        }
    }

}
