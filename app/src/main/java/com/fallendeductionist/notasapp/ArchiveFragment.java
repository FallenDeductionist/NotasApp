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

    private String linker3;
    private RecyclerView notesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linker3 = getArguments().getString("link3");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View archiveView = inflater.inflate(R.layout.fragment_archive, container, false);
        notesList = (RecyclerView) archiveView.findViewById(R.id.notes_list_archive);

        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Note> notes = NoteRepository.listArchive(linker3);
        notesList.setAdapter(new NoteAdapter(notes));

        return archiveView;

    }

    public static ArchiveFragment newInstance(String string) {
        ArchiveFragment archiveFragment = new ArchiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("link3", string);
        archiveFragment.setArguments(bundle);

        return archiveFragment;
    }
}
