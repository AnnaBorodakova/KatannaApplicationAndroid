package com.example.katannaapplicationandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.katannaapplicationandroid.db.AppDatabase;
import com.example.katannaapplicationandroid.db.entity.Film;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private static Context context;

    private static boolean favoriteOnly;

    private static boolean filterByGenreApplied;



    public static TabFragment newInstance(int page, boolean favOnly, Context c, boolean filterByGenre) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TabFragment tab = new TabFragment();
        tab.setArguments(args);
        favoriteOnly = favOnly;
        context = c;
        filterByGenreApplied = filterByGenre;
        return tab;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

 /*   @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        ListView listView = (ListView) view;
        List<Film> films;
        if (mPage==1){
            if (favoriteOnly)
            films = getFavoriteFilms(getWatchedFilms());
            else films = getWatchedFilms();
        }
        else {
            if (favoriteOnly)
                films = getFavoriteFilms(getNotWatchedFilms());
            else films = getNotWatchedFilms();
        }

        FilmAdapter adapter = new FilmAdapter(context, R.layout.list_item, films);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Film selectedFilm = (Film)parent.getItemAtPosition(position);
                Intent intent = new Intent(context, FilmDetailsActivity.class);
                intent.putExtra("film", selectedFilm);
                startActivity(intent);

            }
        };
        listView.setOnItemClickListener(itemListener);
        return view;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listview);
        List<Film> films;
        if (mPage == 1) {
            if (filterByGenreApplied) {
                if (favoriteOnly)
                    films = getFavoriteFilms(filterByGenre(getWatchedFilms()));
                else films = filterByGenre(getWatchedFilms());
            } else {
                if (favoriteOnly)
                    films = getFavoriteFilms(getWatchedFilms());
                else films = getWatchedFilms();
            }
        } else {
            if (filterByGenreApplied) {
                if (favoriteOnly)
                    films = getFavoriteFilms(filterByGenre(getNotWatchedFilms()));
                else films = filterByGenre(getNotWatchedFilms());
            } else {
                if (favoriteOnly)
                    films = getFavoriteFilms(getNotWatchedFilms());
                else films = getNotWatchedFilms();
            }
        }

        FilmAdapter adapter = new FilmAdapter(context, R.layout.list_item, films);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Film selectedFilm = (Film) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, FilmDetailsActivity.class);
                intent.putExtra("film", (Parcelable) selectedFilm);
                startActivity(intent);

            }
        };
        listView.setOnItemClickListener(itemListener);

    }


    public static List<Film> getWatchedFilms() {
        List<Film> films = MainActivity.getDatabase().filmDAO().getAll();
        List<Film> resultFilms = new ArrayList<>();
        for(Film film : films){
            if(film.isStatus()) resultFilms.add(film);
        }
        return resultFilms;
    }


    public List<Film> getNotWatchedFilms() {
        List<Film> films = MainActivity.getDatabase().filmDAO().getAll();
        List<Film> resultFilms = new ArrayList<>();
        for(Film film : films){
            if(!film.isStatus()) resultFilms.add(film);
        }
        return resultFilms;
    }

    private List<Film> getFavoriteFilms(List<Film> films) {
        return films.stream().filter(x -> x.isFavorite()).collect(Collectors.toList());
    }

    private List<Film> filterByGenre(List<Film> films) {
        List<String> genres = MainActivity.getGenresListToFilter();
        List<Film> filteredFilms = new ArrayList<Film>();
        for (int i = 0; i < genres.size(); i++) {
            int finalI = i;
            filteredFilms.addAll(films.stream().filter(x -> x.getGenre().equals(genres.get(finalI))).collect(Collectors.toList()));
        }
        return filteredFilms;
    }
}
