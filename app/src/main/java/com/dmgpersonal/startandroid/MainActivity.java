package com.dmgpersonal.startandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readSettings();
        initView();
    }

    private void readSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        Settings.isDeleteBeforeAdd = sharedPreferences.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
        Settings.isBackAsRemove = sharedPreferences.getBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, true);
        Settings.isAddFragment = sharedPreferences.getBoolean(Settings.IS_ADD_FRAGMENT_USED, false);
        Settings.isBackStack = sharedPreferences.getBoolean(Settings.IS_BACKSTACK_USED, true);
    }

    private void initView() {
        initButtonMain();
        initButtonFavorite();
        initButtonSettings();
        initButtonBack();
    }

    private void initButtonMain() {
        Button buttonMain = findViewById(R.id.buttonMain);
        buttonMain.setOnClickListener(v -> AddFragment(new MainFragment()));
    }

    private void initButtonFavorite() {
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        buttonFavorite.setOnClickListener(v -> AddFragment(new FavoriteFragment()));
    }

    private void initButtonSettings() {
        Button buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(v -> AddFragment(new SettingsFragment()));
    }

    private void initButtonBack() {
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(Settings.isBackAsRemove) {
                Fragment fragment = getVisibleFragment(fragmentManager);
                if(fragment != null) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                } else {
                    fragmentManager.popBackStack();
                }
            }
        });
    }

    private void AddFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(Settings.isDeleteBeforeAdd) {
            Fragment visibleFragment = getVisibleFragment(fragmentManager);
            if(visibleFragment != null) {
                fragmentTransaction.remove(visibleFragment);
            }
        }

        if(Settings.isAddFragment) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
        }

        if(Settings.isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    private Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        int countFragments = fragments.size() - 1;
        for (int i = countFragments; i >= 0 ; i--) {
            Fragment fragment = fragments.get(i);
            if(fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }
}