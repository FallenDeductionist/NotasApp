package com.fallendeductionist.notasapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fallendeductionist.notasapp.models.Note;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private String linker2;
    private RecyclerView notesList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linker2 = getArguments().getString("link2");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View favoriteView = inflater.inflate(R.layout.fragment_favorite, container, false);
        notesList = (RecyclerView) favoriteView.findViewById(R.id.notes_list_favorite);

        notesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Note> notes = NoteRepository.listFavorite(linker2);
        Log.d("lista", notes.toString());
        notesList.setAdapter(new NoteAdapter(notes));

        return favoriteView;
    }

    public static FavoriteFragment newInstance(String string) {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("link2", string);
        favoriteFragment.setArguments(bundle);

        return favoriteFragment;
    }
}
