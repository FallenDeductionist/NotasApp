package com.fallendeductionist.notasapp;

import com.fallendeductionist.notasapp.models.Note;
import com.fallendeductionist.notasapp.models.User;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteRepository {

    public static List<Note> list(String linkCards){

        List<Note> notes = SugarRecord.find(Note.class, "link_cards = ?", linkCards);

        if (notes.size() != 0){
            return notes;
        }

        else{
            ArrayList<Note> emptyNotes = new ArrayList<>() ;
            return emptyNotes;
        }
    }

    public static List<Note> listFavorite(String linkCards){

        List<Note> notes = SugarRecord.find(Note.class, "link_cards = ? and favorite = ?", linkCards, "true");

        if (notes.size() != 0){
            return notes;
        }

        else{
            ArrayList<Note> emptyNotes = new ArrayList<>() ;
            return emptyNotes;
        }
    }

    public static List<Note> listArchive(String linkCards){

        List<Note> notes = SugarRecord.find(Note.class, "link_cards = ? and archive = ?", linkCards, "true");

        if (notes.size() != 0){
            return notes;
        }

        else{
            ArrayList<Note> emptyNotes = new ArrayList<>() ;
            return emptyNotes;
        }
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }

    public static void create(String date, String title, String description, Boolean favorite, Boolean archive, Long linkCards){
        Note note = new Note(date, title, description, favorite, archive, linkCards);
        SugarRecord.save(note);
    }

    public static void updateFavorite(Boolean favorite, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setFavorite(favorite);
        SugarRecord.save(note);
    }

    public static void updateArchive(Boolean archive, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setArchive(archive);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }
}
