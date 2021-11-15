package com.example.deardiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.deardiary.Models.Note;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setupCreateButton();
    }

    private void setupCreateButton(){
        Button createButton = (Button) findViewById(R.id.CreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("note", getNewNote());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Note getNewNote(){
        EditText titleField = (EditText)findViewById(R.id.TitleField);
        String title = titleField.getText().toString();

        EditText descriptionField = (EditText)findViewById(R.id.DescriptionField);
        String description = descriptionField.getText().toString();

        RadioGroup radioGroupMoods = (RadioGroup) findViewById(R.id.radioGroupMoods);
        int selectedMoodRadioID = radioGroupMoods.getCheckedRadioButtonId();
        String mood = "";
        switch(selectedMoodRadioID) {
            case R.id.radioOptionHappy:
                mood = "HAPPY";
                break;
            case R.id.radioOptionSad:
                mood = "SAD";
                break;
            case R.id.radioOptionIdk:
                mood = "OK";
                break;
        }

        RadioGroup radioGroupIcons = (RadioGroup) findViewById(R.id.radioGroupIcons);
        int selectedIconRadioID = radioGroupIcons.getCheckedRadioButtonId();
        int icon = 0;
        switch(selectedIconRadioID) {
            case R.id.radioOptionIcon1:
                icon = 1;
                break;
            case R.id.radioOptionIcon2:
                icon = 2;
                break;
            case R.id.radioOptionIcon3:
                icon = 3;
                break;
        }
        return new Note(title, mood, description, icon);
    }
}