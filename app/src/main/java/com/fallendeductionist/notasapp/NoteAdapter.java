package com.fallendeductionist.notasapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fallendeductionist.notasapp.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    public void setNotes (List<Note> notes){
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date;
        public TextView title;
        public TextView description;
        public CheckBox favorite;
        public CheckBox archive;

        public ViewHolder(View exampleView){
            super(exampleView);
            date = (TextView) exampleView.findViewById(R.id.note_date);
            title = (TextView) exampleView.findViewById(R.id.note_title);
            description = (TextView) exampleView.findViewById(R.id.note_content);
            favorite = (CheckBox) exampleView.findViewById(R.id.favorite_note);
            archive = (CheckBox) exampleView.findViewById(R.id.archive_note);
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View exampleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_note, parent, false);
        return new ViewHolder(exampleView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, int position) {
        final Note note = this.notes.get(position);
        viewHolder.date.setText(note.getDate());
        viewHolder.title.setText(note.getTitle());
        viewHolder.description.setText(note.getDescription());
        viewHolder.favorite.setChecked(note.getFavorite());
        viewHolder.favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setFavorite(b);
                NoteRepository.updateFavorite(b, note.getId());
                Log.d("test star", NoteRepository.list("1").toString());
                Log.d("test archive", note.getArchive().toString());
            }
        });
        viewHolder.archive.setChecked(note.getArchive());
        viewHolder.archive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setArchive(b);
                NoteRepository.updateArchive(b, note.getId());
                Log.d("test star", note.getFavorite().toString());
                Log.d("test archive", note.getArchive().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }
}
