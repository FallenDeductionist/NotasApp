package com.fallendeductionist.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fallendeductionist.notasapp.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {

    private EditText titleNote;

    private EditText descriptionNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        titleNote = (EditText)findViewById(R.id.title_note);
        descriptionNote = (EditText)findViewById(R.id.description_note);

        Note note = new Note();
        String test = NoteRepository.list("3").toString();
        Log.d("test lista", test);

    }

    public void callRegister(View view){

        String title = titleNote.getText().toString();
        String description = descriptionNote.getText().toString();

        Long linkCard = this.getIntent().getExtras().getLong("linking");

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Every field must be completed!", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date current = new Date();
        String formattedDate = sdf.format(current);

        NoteRepository.create(formattedDate, title, description, false, false, linkCard);


        Toast.makeText(this, "Note succesfully registered!", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);

        finish();
    }
}
