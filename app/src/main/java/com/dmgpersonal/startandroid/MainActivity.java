package com.dmgpersonal.startandroid;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements Constants {

    private EditText txtName;
    private Account account;
    private final ActivityResultLauncher<Account> runSettings = registerForActivityResult(new SettingsContract(),
            result -> {
                account = result;
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(account == null) account = new Account();
        initView();
    }

    private void initView() {
        MaterialButton btnGreetings = findViewById(R.id.btnGreetings);
        txtName = findViewById(R.id.textName);
        if(account != null) {
            txtName.setText(account.getName());
        }
        final TextView txtGreetings = findViewById(R.id.textHello);
        btnGreetings.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            if (name.isEmpty()) return;
            String sayHello = getString(R.string.say_hello) + name;
            txtGreetings.setText(sayHello);
        });

        MaterialButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            if(account != null) account.setName(txtName.getText().toString());
            runSettings.launch(account);
        });

        MaterialButton btnBash = findViewById(R.id.btnBash);
        btnBash.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://bash.org.ru");
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }
}