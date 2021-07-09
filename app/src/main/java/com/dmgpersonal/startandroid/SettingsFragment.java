package com.dmgpersonal.startandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onResume() {
        readSettings();
        super.onResume();
    }

    private void readSettings() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Settings.isDeleteBeforeAdd = sharedPreferences.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
        Settings.isBackAsRemove = sharedPreferences.getBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, true);
        Settings.isAddFragment = sharedPreferences.getBoolean(Settings.IS_ADD_FRAGMENT_USED, false);
        Settings.isBackStack = sharedPreferences.getBoolean(Settings.IS_BACKSTACK_USED, true);
    }

    private void initView(View view) {
        initSwitchBackStack(view);
        initRadioAdd(view);
        initRadioReplace(view);
        initSwitchBackAsRemove(view);
        initSwitchDeleteBeforeAdd(view);
    }

    private void initSwitchBackStack(View view) {
        SwitchCompat switchBackStack = view.findViewById(R.id.switchBackStack);

        switchBackStack.setChecked(Settings.isBackStack);

        switchBackStack.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Settings.isBackStack = isChecked;
            writeSettings();
        });
    }

    private void writeSettings() {
        requireActivity()
                .getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(Settings.IS_ADD_FRAGMENT_USED, Settings.isAddFragment)
                .putBoolean(Settings.IS_BACK_AS_REMOVE_FRAGMENT, Settings.isBackAsRemove)
                .putBoolean(Settings.IS_BACKSTACK_USED, Settings.isBackStack)
                .putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, Settings.isDeleteBeforeAdd)
                .apply();
    }

    private void initRadioAdd(View view) {
        RadioButton radioButtonAdd = view.findViewById(R.id.radioButtonAdd);
        radioButtonAdd.setChecked(Settings.isAddFragment);
        radioButtonAdd.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Settings.isAddFragment = isChecked;
            writeSettings();
        });
    }

    private void initRadioReplace(View view) {
        RadioButton radioButtonReplace = view.findViewById(R.id.radioButtonReplace);
        radioButtonReplace.setChecked(!Settings.isAddFragment);
        radioButtonReplace.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Settings.isAddFragment = !isChecked;
            writeSettings();
        });
    }

    private void initSwitchBackAsRemove(View view) {
        SwitchCompat switchBackAsRemove = view.findViewById(R.id.switchBackAsRemove);
        switchBackAsRemove.setChecked(Settings.isBackAsRemove);
        switchBackAsRemove.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Settings.isBackAsRemove = isChecked;
            writeSettings();
        });
    }

    private void initSwitchDeleteBeforeAdd(View view) {
        SwitchCompat switchDeleteBeforeAdd = view.findViewById(R.id.switchDeleteBeforeAdd);
        switchDeleteBeforeAdd.setChecked(Settings.isDeleteBeforeAdd);
        switchDeleteBeforeAdd.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Settings.isDeleteBeforeAdd = isChecked;
            writeSettings();
        });
    }

}
