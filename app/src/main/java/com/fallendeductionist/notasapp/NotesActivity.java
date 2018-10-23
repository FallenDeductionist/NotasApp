package com.fallendeductionist.notasapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.fallendeductionist.notasapp.models.Note;

import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView notesList;
    private static final int REGISTER_FORM_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        final String fullname = this.getIntent().getExtras().getString("fullname");
        long link = this.getIntent().getExtras().getLong("identifier");

        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Notas");
        setSupportActionBar(toolbar);
        Toolbar toolbar1 = findViewById(R.id.toolbar3);
        toolbar1.setTitle("Bienvenido " + fullname);
        setSupportActionBar(toolbar1);

        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("rofl", link);
        homeFragment.setArguments(bundle);

        FavoriteFragment favoriteFragment = new FavoriteFragment();
        Bundle bundle2 = new Bundle();
        bundle.putLong("rofl", link);
        favoriteFragment.setArguments(bundle2);

        ArchiveFragment archiveFragment = new ArchiveFragment();
        Bundle bundle3 = new Bundle();
        bundle.putLong("rofl", link);
        archiveFragment.setArguments(bundle3);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

    }

    public void refreshData() {
        NoteAdapter adapter = (NoteAdapter) notesList.getAdapter();
        Note note = new Note();
        Long linking = this.getIntent().getExtras().getLong("identifier");
        List<Note> notes = NoteRepository.list(linking.toString());
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    public void callRegister(View view) {
        Long linking = getIntent().getExtras().getLong("linking");
        Log.d("link", linking.toString());

        Intent intent2 = new Intent(this, AddNotesActivity.class);
        intent2.putExtra("linking", linking);
        startActivityForResult(intent2, REGISTER_FORM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REGISTER_FORM_REQUEST:
                if (resultCode == RESULT_OK) {
                    refreshData();
                }
                break;
            default:
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_favorite:
                            selectedFragment = new FavoriteFragment();
                            break;

                        case R.id.nav_archive:
                            selectedFragment = new ArchiveFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}
