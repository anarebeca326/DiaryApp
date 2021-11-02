package com.example.deardiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deardiary.Models.Note;

import org.w3c.dom.Text;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        if(getIntent().getExtras() != null) {
            Note note = (Note) getIntent().getSerializableExtra("note");
            TextView titleView = findViewById(R.id.note_title);
            titleView.setText(note.title);
            TextView moodView = findViewById(R.id.mood);
            moodView.setText("Mood: " + note.mood);
            TextView descriptionView = findViewById(R.id.description);
            descriptionView.setText(note.description);
        }
    }
}