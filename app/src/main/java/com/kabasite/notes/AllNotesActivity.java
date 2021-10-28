package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class AllNotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        ArrayList<Note> notes = new ArrayList<>();
        notes = new ArrayList<>();
        notes.add(new Note("Hello There", "General Kenobi"));
        notes.add(new Note("Hello There General kenobi ah general gracious how are you you must be tired af",
                "General Kenobi: yeah I am tired AF, how are you doing \n I am doing my part in the comming war by \n comming all over the place"));

        recyclerView = findViewById(R.id.recViewIdAllNotes);
        adapter = new RecViewAdapter();
        adapter.setNotes(notes);
        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "onCreate: setNotesCalled from AllNotesActivity");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}