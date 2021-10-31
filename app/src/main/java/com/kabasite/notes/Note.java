package com.kabasite.notes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note() {
        title = "";
        content = "";
    }

    public String toSerialized(){
        return new Gson().toJson(this);
    }

    public static Note fromSerialized(String string){
        return new Gson().fromJson(string,new TypeToken<Note>(){}.getType());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
