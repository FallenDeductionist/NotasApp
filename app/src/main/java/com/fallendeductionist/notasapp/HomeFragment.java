package com.fallendeductionist.notasapp;

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

import com.fallendeductionist.notasapp.models.Note;

import java.util.List;

public class HomeFragment extends Fragment {

    private String linker;
    private RecyclerView notesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("hola", getArguments().getString("link1"));
            Bundle bundle = this.getArguments();
            linker = bundle.getString("link1");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        notesList = (RecyclerView) homeView.findViewById(R.id.notes_list_home);
        
        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Note> notes = NoteRepository.list(linker);
        Log.d("hola", notes.toString());
        notesList.setAdapter(new NoteAdapter(notes));

        return homeView;
    }

    public static HomeFragment newInstance(String string) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("link1", string);
        homeFragment.setArguments(bundle);

        return homeFragment;
    }



}