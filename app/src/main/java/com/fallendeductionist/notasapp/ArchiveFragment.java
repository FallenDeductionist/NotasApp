package com.fallendeductionist.notasapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fallendeductionist.notasapp.models.Note;

import java.util.List;

public class ArchiveFragment extends Fragment {

    private RecyclerView notesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_archive, container, false);
        notesList = (RecyclerView) getView().findViewById(R.id.notes_list_archive);

        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        Long linking = getArguments().getLong("linking");
        List<Note> notes = NoteRepository.listArchive(linking.toString());
        notesList.setAdapter(new NoteAdapter(notes));

        return homeView;
    }
}
