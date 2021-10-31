package com.kabasite.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import static com.kabasite.notes.AllNotesActivity.KEY_NOTE_OBJECT;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;
    private Context mContext;

    public void setContext(Context context){
        this.mContext = context;
    }

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
        holder.imgDeleteNote.setOnClickListener(v -> {
            //todo add warning dialog before delete note
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Delete Note: \""+notes.get(position).getTitle()+"\"?");
            builder.setNegativeButton("No", (dialog, which) -> {

            });
            builder.setPositiveButton("Yes", (dialog, which) -> {
                if(!Utils.deleteNote(notes.get(position))){
                    Log.e("myError", "\"onBindViewHolder: Error in deleting note from Utils class called from recView Adapter\"?");
                }
                notes = Utils.getAllNotes();
                notifyDataSetChanged();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        holder.imgEditNote.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EditNoteActivity.class);
            intent.putExtra(KEY_NOTE_OBJECT, notes.get(position).toSerialized());
            mContext.startActivity(intent);
        });

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
        private ImageView imgDeleteNote, imgEditNote;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtIdNoteTitle);
            txtContent = itemView.findViewById(R.id.txtIdNoteText);
            imgDeleteNote = itemView.findViewById(R.id.imgIdDeleteNote);
            imgEditNote = itemView.findViewById(R.id.imgIdEditNote);
        }
    }

}
