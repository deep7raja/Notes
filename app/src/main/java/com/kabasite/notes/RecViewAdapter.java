package com.kabasite.notes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecViewAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(notes.get(position).getTitle());
        holder.txtContent.setText(notes.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(ArrayList<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public RecViewAdapter() {

    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtContent;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtIdNoteTitle);
            txtContent = itemView.findViewById(R.id.txtIdNoteText);
            if(txtTitle == null){
                Log.d("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", "onBindViewHolder: nope");
            }
            else{
                Log.d("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC", "onBindViewHolder: nope");
            }
            Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "ViewHolder: title and content reference set");
        }
    }

}
