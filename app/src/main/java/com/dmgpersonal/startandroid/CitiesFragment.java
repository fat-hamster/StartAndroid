package com.dmgpersonal.startandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitiesFragment extends Fragment {

    private boolean isLandscape;
    public static final String CURRENT_CITY = "CurrentCity";
    private City currentCity;
    private Bundle savedInstanceState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        initList(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(savedInstanceState != null) {
            currentCity = savedInstanceState.getParcelable(CURRENT_CITY);
        } else {
            currentCity = new City(0, getResources().getStringArray(R.array.cities)[0]);
        }
        if(isLandscape) {
            showLandCoatOfArms(currentCity);
        }
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
//        if(isLandscape) {
//            showLandCoatOfArms(0);
//        }
//    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentCity = new City(fi, getResources().getStringArray(R.array.cities)[fi]);
                showCoatOfArms(currentCity);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_CITY, currentCity);
        super.onSaveInstanceState(outState);
    }

    private void showCoatOfArms(City currentCity) {
        if(isLandscape) {
            showLandCoatOfArms(currentCity);
        } else {
            showPortCoatOfArms(currentCity);
        }
    }

    private void showLandCoatOfArms(City currentCity) {
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(currentCity);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.coat_of_arms, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortCoatOfArms(City currentCity) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CoatOfArmsActivity.class);
        intent.putExtra(CoatOfArmsFragment.ARG_INDEX, currentCity);
        startActivity(intent);
    }
}