package com.example.deardiary;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.deardiary.Models.Note;

public class UpdateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        if(getIntent().getExtras() != null) {
            Note note = (Note) getIntent().getSerializableExtra("note");
            setupFields(note);
            setupUpdateButton();
        }
    }

    private void setupUpdateButton(){
        Button updateButton = (Button) findViewById(R.id.UpdateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note updatedNote = getUpdatedNote();
                Intent intent = new Intent();
                intent.putExtra("note", updatedNote);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setupFields(Note note){
        EditText titleField = (EditText)findViewById(R.id.TitleField);
        titleField.setText(note.title);
        EditText descriptionField = (EditText)findViewById(R.id.DescriptionField);
        descriptionField.setText(note.description);

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

    private Note getUpdatedNote(){
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