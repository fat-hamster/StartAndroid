package com.dmgpersonal.startandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Constants {
    private EditText editName;
    private EditText editSurName;
    private EditText editAge;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        initView();

        Account account = getIntent().getExtras().getParcelable(YOUR_ACCOUNT);
        populateView(account);

        MaterialButton btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            Intent intentResult = new Intent();
            intentResult.putExtra(YOUR_ACCOUNT, createAccount());
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    private Account createAccount() {
        Account resultAccount = new Account(
                editName.getText().toString(),
                editSurName.getText().toString(),
                Integer.parseInt(editAge.getText().toString()),
                editEmail.getText().toString()
        );
        return resultAccount;
    }

    private void populateView(Account account) {
        editName.setText(account.getName());
        editSurName.setText(account.getSurName());
        editAge.setText(String.format(Locale.getDefault(), "%d", account.getAge()));
        editEmail.setText(account.getEmail());
    }

    private void initView() {
        editName = findViewById(R.id.editName);
        editSurName = findViewById(R.id.editSurname);
        editAge = findViewById(R.id.editAge);
        editEmail = findViewById(R.id.editEmail);
    }
}