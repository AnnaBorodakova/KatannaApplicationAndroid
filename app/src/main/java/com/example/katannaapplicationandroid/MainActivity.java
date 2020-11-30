package com.example.katannaapplicationandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.katannaapplicationandroid.Entity.Film;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    private static TabAdapter tabAdapter;
    private static TabLayoutMediator tabLayoutMediator;
    private boolean favouriteShowed = false;
    private FilterByGenreDialogFragment dialog;
    private static List<String> genresListToFilter = new ArrayList<>();

    public static List<String> getGenresListToFilter() {
        return genresListToFilter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);

        tabAdapter = new TabAdapter(this, this);
        viewPager.setAdapter(tabAdapter);
        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position==0) tab.setText(getResources().getString(R.string.watched_tab));
                       else tab.setText(getResources().getString(R.string.not_watched_tab));
                    }
                });
        tabLayoutMediator.attach();

      /*  tabAdapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this,
                new String[]{getResources().getString(R.string.watched_tab), getResources().getString(R.string.not_watched_tab)});


        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);*/


    }


    public void onFloatingButtonClick(View view) {
        Intent intent = new Intent(this, FilmDetailsActivity.class);
        startActivity(intent);
    }

    public void showDialog(View view) {

        dialog = new FilterByGenreDialogFragment();


       // LayoutInflater factory = this.getLayoutInflater();
      //  View view1 = factory.inflate(R.layout.dialog, null);

       // ListView genres = view1.findViewById(R.id.genres_listview);

        // создаем адаптер
       // ArrayAdapter<String> adapter = new ArrayAdapter(this,
               // R.layout.list_item);
        // устанавливаем для списка адаптер
       // genres.setAdapter(adapter);



        dialog.show(getSupportFragmentManager(), "custom");



    }



    public void onFavoriteButtonClick(View view) {

        if (!favouriteShowed) {
            tabAdapter.setFavoriteOnly(true);
            favouriteShowed = true;
        }
        else {
            tabAdapter.setFavoriteOnly(false);
            favouriteShowed = false;
        }
        viewPager.setAdapter(tabAdapter);
    }

    public void onApplyButtonClick(View view) {
        dialog.dismiss();
       // genresListToFilter = dialog.getSelectedGenres();
        if (genresListToFilter.size()!=0)
            tabAdapter.setFilterByGenreApplied(true);
        else tabAdapter.setFilterByGenreApplied(false);
        viewPager.setAdapter(tabAdapter);

    }




}