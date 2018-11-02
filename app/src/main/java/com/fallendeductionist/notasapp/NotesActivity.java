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

    private static final int REGISTER_FORM_REQUEST = 100;
    private Fragment homeFragment;
    private Fragment favoriteFragment;
    private Fragment archiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        final String fullname = this.getIntent().getExtras().getString("fullname");
        final Long link = this.getIntent().getExtras().getLong("identifier");
        String linking = String.valueOf(link);
        Log.d("link", linking);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Notas");
        setSupportActionBar(toolbar);
        Toolbar toolbar1 = findViewById(R.id.toolbar3);
        toolbar1.setTitle("Bienvenido " + fullname);
        setSupportActionBar(toolbar1);

        homeFragment = HomeFragment.newInstance(linking);
        favoriteFragment = FavoriteFragment.newInstance(linking);
        archiveFragment = ArchiveFragment.newInstance(linking);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

    }

    public void refreshData() {
        Long link = this.getIntent().getExtras().getLong("identifier");
        String linking = String.valueOf(link);
        Log.d("link", linking);
        homeFragment = HomeFragment.newInstance(linking);
        favoriteFragment = FavoriteFragment.newInstance(linking);
        archiveFragment = ArchiveFragment.newInstance(linking);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
    }

    public void callRegister(View view) {
        Long link = getIntent().getExtras().getLong("identifier");
        Log.d("link", link.toString());

        Intent intent2 = new Intent(this, AddNotesActivity.class);
        intent2.putExtra("linking", link);
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
                            selectedFragment = homeFragment;
                            break;

                        case R.id.nav_favorite:
                            selectedFragment = favoriteFragment;
                            break;

                        case R.id.nav_archive:
                            selectedFragment = archiveFragment;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}
