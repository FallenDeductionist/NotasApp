package com.fallendeductionist.notasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fallendeductionist.notasapp.models.Note;

import java.util.List;

public class HomeFragment extends Fragment {

    private Long hola;
    private RecyclerView notesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         hola = getArguments().getLong("rofl");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        notesList = (RecyclerView) homeView.findViewById(R.id.notes_list_home);
        
        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));;
        List<Note> notes = NoteRepository.list(hola.toString());
        notesList.setAdapter(new NoteAdapter(notes));

        return homeView;
    }


}