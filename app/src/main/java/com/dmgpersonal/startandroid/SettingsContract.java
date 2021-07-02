package com.dmgpersonal.startandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingsContract extends ActivityResultContract<Account, Account> implements Constants{
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Account input) {
        return new Intent(context, SettingsActivity.class).putExtra(YOUR_ACCOUNT, input);
    }

    @Override
    public Account parseResult(int resultCode, @Nullable Intent intent) {
        if(resultCode != Activity.RESULT_OK) return null;
        return (Account) (intent != null ? intent.getParcelableExtra(YOUR_ACCOUNT) : null);
    }
}
