package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import static com.kabasite.notes.AllNotesActivity.KEY_NOTE_OBJECT;

public class EditNoteActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextContent;
    private Button btnSave, btnDiscard;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextTitle = findViewById(R.id.editIdNoteEditTitle);
        editTextContent = findViewById(R.id.editIdNoteEditContent);
        btnSave = findViewById(R.id.btnIdNoteEditSave);
        btnDiscard = findViewById(R.id.btnIdNoteEditDiscard);
        setInitialText();
        btnSave.setOnClickListener(v -> saveNote());
        btnDiscard.setOnClickListener(v -> gotoAllNotes());
    }

    private void saveNote(){
        note.setTitle(editTextTitle.getText().toString());
        note.setContent(editTextContent.getText().toString());
        Utils.addNote(note);
        gotoAllNotes();
    }

    private void gotoAllNotes(){
        Intent intent = new Intent(this, AllNotesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setInitialText(){
        note = Note.fromSerialized(getIntent().getStringExtra(KEY_NOTE_OBJECT));
        editTextTitle.setText(note.getTitle());
        editTextContent.setText(note.getContent());
    }
}