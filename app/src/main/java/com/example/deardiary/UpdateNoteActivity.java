package com.example.deardiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.deardiary.Models.Note;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        if(getIntent().getExtras() != null) {
            Note note = (Note) getIntent().getSerializableExtra("note");
            EditText titleField = (EditText)findViewById(R.id.TitleField);
            titleField.setText(note.title);
            EditText descriptionField = (EditText)findViewById(R.id.DescriptionField);
            titleField.setText(note.title);

            switch(note.getMood()) {
                case "HAPPY":
                    RadioButton radioHappy = (RadioButton) findViewById(R.id.radioOptionHappy);
                    radioHappy.setChecked(true);
                    break;
                case "SAD":
                    RadioButton radioSad = (RadioButton) findViewById(R.id.radioOptionSad);
                    radioSad.setChecked(true);
                    break;
                case "OK":
                    RadioButton radioOk = (RadioButton) findViewById(R.id.radioOptionIdk);
                    radioOk.setChecked(true);
                    break;
            }

            switch(note.getIcon()) {
                case 1:
                    RadioButton radioIcon1 = (RadioButton) findViewById(R.id.radioOptionIcon1);
                    radioIcon1.setChecked(true);
                    break;
                case 2:
                    RadioButton radioIcon2 = (RadioButton) findViewById(R.id.radioOptionIcon2);
                    radioIcon2.setChecked(true);
                    break;
                case 3:
                    RadioButton radioIcon3 = (RadioButton) findViewById(R.id.radioOptionIcon3);
                    radioIcon3.setChecked(true);
                    break;
            }
        }
    }
}