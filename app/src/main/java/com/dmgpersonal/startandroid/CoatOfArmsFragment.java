package com.dmgpersonal.startandroid;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CoatOfArmsFragment extends Fragment {

    public static final String ARG_INDEX = "index";
    private City city;

    public static CoatOfArmsFragment newInstance(City city) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, city);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            city = getArguments().getParcelable(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
        AppCompatImageView imageView = view.findViewById(R.id.coat_of_arms);
        @SuppressLint("Recycle") TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        imageView.setImageResource(images.getResourceId(city.getImageIndex(), -1));
        TextView tv = view.findViewById(R.id.textView);
        tv.setText(city.getCityName());
        return view;
    }
}