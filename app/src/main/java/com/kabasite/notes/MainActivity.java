package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartButton = findViewById(R.id.btnIdStartApp);
        btnStartButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AllNotesActivity.class);
            startActivity(intent);
        });
    }
}