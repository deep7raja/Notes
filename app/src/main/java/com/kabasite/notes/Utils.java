package com.kabasite.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Array;
import java.util.ArrayList;

public class Utils {
    public static final String KEY_ALL_NOTES_IN_DB = "all_notes_in_db";
    private static SharedPreferences db;
    private static Utils instance;

    private Utils(Context context){
        db = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if(!db.getString("KEY_IS_FIRST_RUN", "").equals("1")){
            db.edit().putString("KEY_IS_FIRST_RUN", "1").commit();
            setAllNotes(new ArrayList<>());
        }
    }

    public static Utils getInstance(Context context){
        if(instance == null){
            instance = new Utils(context);
        }
        return instance;
    }

    public static ArrayList<Note> getAllNotes(){
        String tempString = db.getString(KEY_ALL_NOTES_IN_DB, "");
        if("" == tempString){
            return new ArrayList<>();
        }
        if(null != tempString){
            Log.d("mysuc", "getAllNotes: returnd success");
            return new Gson().fromJson(tempString, new TypeToken<ArrayList<Note>>(){}.getType());
        }
        return null;
    }

    public static void setAllNotes(ArrayList<Note> notes){
        db.edit().remove(KEY_ALL_NOTES_IN_DB).putString(KEY_ALL_NOTES_IN_DB, new Gson().toJson(notes)).commit();
        Log.d("mysuc", "setAllNotes: returned successs");
    }

    public static boolean addNote(Note note) {
        ArrayList<Note> notes = getAllNotes();
        if(null != notes){
            notes.add(0, note);
            setAllNotes(notes);
            return true;
        }
        return false;
    }

    public static boolean deleteNote(Note note){
        ArrayList<Note> notes = getAllNotes();
        if(null != notes){
            for(Note n:notes){
                if(n.getTitle().equals(note.getTitle()) && n.getContent().equals(note.getContent())){
                    if(notes.remove(n)){
                        setAllNotes(notes);
                        return true;
                    }
                    else{
                        Log.e("myError", "deleteNote: BBBb");
                    }
                }
            }
            Log.e("myError", "deleteNote: AAAa");
        }
        return false;
    }

    public static void clearAllData() {
        db.edit().remove(KEY_ALL_NOTES_IN_DB).commit();
    }
}
