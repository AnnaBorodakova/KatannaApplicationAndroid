package com.example.katannaapplicationandroid;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FilterByGenreDialogFragment extends DialogFragment {

/*    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.dialog);


        return builder.create();
    }*/
    private ListView listView;
    private static List<String> selectedGenres = new ArrayList<String>();

    public FilterByGenreDialogFragment() {
        // Required empty public constructor
    }

    public List<String> getSelectedGenres() {
        return selectedGenres;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.genres_listview);
      //  List<String> strings = Arrays.asList("Hello", "World", "Bye");
        CharSequence[] genres = getResources().getTextArray(R.array.genres);
        List<String> genresList = new ArrayList<>();
        for (CharSequence g: genres) {
            genresList.add(g.toString());
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice, genres);
        CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), R.layout.dialog_list_item, genresList,  MainActivity.getGenresListToFilter());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                SparseBooleanArray sp=listView.getCheckedItemPositions();
                MainActivity.getGenresListToFilter().clear();
              //  selectedGenres.clear();

                for(int i=0;i<sp.size();i++)
                {
                    MainActivity.getGenresListToFilter().add(genres[sp.keyAt(i)].toString());
                    //selectedGenres.add(genres[sp.keyAt(i)].toString());
                }

            }
        });


    }





}
