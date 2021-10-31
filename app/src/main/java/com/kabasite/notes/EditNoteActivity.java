package com.kabasite.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        editTextContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    saveNote();
                }
                return false;
            }
        });
        btnSave.setOnClickListener(v -> saveNote());
        btnDiscard.setOnClickListener(v -> gotoAllNotes());
    }

    private void saveNote(){
        note.setTitle(editTextTitle.getText().toString());
        note.setContent(editTextContent.getText().toString());
        if(!Utils.addNote(note)){
            Toast.makeText(this, "Error in saving the note please try again", Toast.LENGTH_SHORT).show();
            return;
        }
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