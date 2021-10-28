package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnStartButton, btnAllDummyData, btnDeleteAllNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.getInstance(this);
        btnStartButton = findViewById(R.id.btnIdStartApp);
        btnAllDummyData = findViewById(R.id.btnIdAddDummyData);
        btnDeleteAllNotes = findViewById(R.id.btnIdDeleteAllNotes);

        btnStartButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AllNotesActivity.class);
            startActivity(intent);
        });
        btnAllDummyData.setOnClickListener(v -> {
            ArrayList<Note> notes = new ArrayList<>();
            notes = new ArrayList<>();
            notes.add(new Note("Hello There", "General Kenobi"));
            notes.add(new Note("Hello There General kenobi ah general gracious how are you you must be tired af",
                    "General Kenobi: yeah I am tired AF, how are you doing \n I am doing my part in the comming war by \n comming all over the place"));
            Utils.setAllNotes(notes);
        });
        btnDeleteAllNotes.setOnClickListener(v -> {
            Utils.clearAllData();
        });
    }
}