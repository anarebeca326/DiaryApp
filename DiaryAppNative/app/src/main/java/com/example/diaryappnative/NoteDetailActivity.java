package com.example.diaryappnative;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.diaryappnative.Models.Note;

import org.w3c.dom.Text;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        if(getIntent().getExtras() != null) {
            Note note = (Note) getIntent().getSerializableExtra("note");
            setupFields(note);
            setupEditButton(note);
        }
    }

    private void setupEditButton(Note note){
        ActivityResultLauncher<Intent> updateActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Note updatedNote = (Note) data.getSerializableExtra("note");
                            Intent intent = new Intent();
                            intent.putExtra("note", updatedNote);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                });

        Button editButton = findViewById(R.id.EditButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteDetailActivity.this, UpdateNoteActivity.class);
                intent.putExtra("note", note);
                updateActivityResultLauncher.launch(intent);
            }
        });
    }

    private void setupFields(Note note){
        TextView titleView = findViewById(R.id.note_title);
        titleView.setText(note.title);
        TextView moodView = findViewById(R.id.mood);
        moodView.setText("Mood: " + note.mood);
        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(note.description);
    }
}