package com.example.katannaapplicationandroid;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter {
    final int PAGE_COUNT = 2;
    private String[] tabTitles;
    private Context context;
    private boolean favoriteOnly = false;
    private boolean filterByGenreApplied = false;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

 /*   public TabAdapter(@NonNull FragmentManager fm, int behavior, Context context, String[] tabTitles) {
        super(fm, behavior);
        this.context = context;
        this.tabTitles = tabTitles;
    }*/


    /*@Override public int getCount() {
        return PAGE_COUNT;
    }

    @Override public Fragment getItem(int position) {
        return TabFragment.newInstance(position+1, context, favoriteOnly);
    }

    @Override public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }*/

    public void setFavoriteOnly(boolean favoriteOnly) {
        this.favoriteOnly = favoriteOnly;
       // notifyDataSetChanged();
    }

    public void setFilterByGenreApplied(boolean filterByGenreApplied) {
        this.filterByGenreApplied = filterByGenreApplied;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TabFragment.newInstance(position+1, favoriteOnly, context, filterByGenreApplied);
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

    public String getPageTitle(int position) {
        return tabTitles[position];
    }

}
