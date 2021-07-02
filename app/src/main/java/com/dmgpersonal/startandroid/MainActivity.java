package com.dmgpersonal.startandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final static String TEXT = "PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Uri uri = Uri.parse("example://intent");
            Intent runEchoIntent = new Intent(Intent.ACTION_VIEW, uri);
            runEchoIntent.putExtra(TEXT, editText.getText().toString());
            startActivity(runEchoIntent);
        });
    }
}