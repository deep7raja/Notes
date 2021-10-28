package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class AllNotesActivity extends AppCompatActivity {
    public static final String KEY_NOTE_OBJECT = "note object passed through intent";
    private ImageView imgAddNewNote;
    private RecyclerView recyclerView;
    RecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        recyclerView = findViewById(R.id.recViewIdAllNotes);
        imgAddNewNote = findViewById(R.id.imgIdAddNote);


        adapter = new RecViewAdapter();
        ArrayList<Note> note = Utils.getAllNotes();
        if(null == note){
            note = new ArrayList<Note>();
            Log.e("myError", "onCreate: empty note passed to setNote in AllNotesActivity");
        }
        adapter.setNotes(note);
        Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "onCreate: setNotesCalled from AllNotesActivity");
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayout);

        imgAddNewNote.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditNoteActivity.class);
            intent.putExtra(KEY_NOTE_OBJECT, new Note().toSerialized());
            startActivity(intent);
        });
    }
}