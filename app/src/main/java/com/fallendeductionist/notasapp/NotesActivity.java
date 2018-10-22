package com.fallendeductionist.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Notas");
        setSupportActionBar(toolbar);
        Toolbar toolbar1 = findViewById(R.id.toolbar3);
        toolbar1.setTitle("Bienvenido " + fullname);
        setSupportActionBar(toolbar1);

            notesList = (RecyclerView) findViewById(R.id.notes_list);
            notesList.setLayoutManager(new LinearLayoutManager(this));
            Long linking = this.getIntent().getExtras().getLong("identifier");
            List<Note> notes = NoteRepository.list(linking.toString());
            notesList.setAdapter(new NoteAdapter(notes));
        }


    public void refreshData(){
        NoteAdapter adapter = (NoteAdapter) notesList.getAdapter();
        Note note = new Note();
        Long linking = this.getIntent().getExtras().getLong("identifier");
        List<Note> notes = NoteRepository.list(linking.toString());
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }

    public void callRegister(View view){
        Long linking = this.getIntent().getExtras().getLong("identifier");
        Log.d("link", linking.toString());

        Intent intent2 = new Intent(this, AddNotesActivity.class);
        intent2.putExtra("linking", linking);
        startActivityForResult(intent2, REGISTER_FORM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

}
