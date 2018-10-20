package com.fallendeductionist.notasapp;

import com.fallendeductionist.notasapp.models.Note;
import com.orm.SugarRecord;

import java.util.List;

public class NoteRepository {

    public static List<Note> list(){
        List<Note> notes = SugarRecord.listAll(Note.class);
        return notes;
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }

    public static void create(String title, String description, Boolean favorite){
        Note note = new Note(title, description, favorite);
        SugarRecord.save(note);
    }

    public static void update(String title, String description, Boolean favorite, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setTitle(title);
        note.setDescription(description);
        note.setFavorite(favorite);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }
}
