package com.example.diaryappnative;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.diaryappnative.Models.Note;

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

        String mood = note.getMood();

        switch(mood) {
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
    }

    private Note getUpdatedNote(){
        EditText titleField = (EditText)findViewById(R.id.TitleField);
        String title = titleField.getText().toString();

        EditText descriptionField = (EditText)findViewById(R.id.DescriptionField);
        String description = descriptionField.getText().toString();

        RadioGroup radioGroupMoods = (RadioGroup) findViewById(R.id.radioGroupMoods);
        int selectedMoodRadioID = radioGroupMoods.getCheckedRadioButtonId();
        String mood = "";
        int icon = 0;
        switch(selectedMoodRadioID) {
            case R.id.radioOptionHappy:
                mood = "HAPPY";
                icon = R.drawable.happy;
                break;
            case R.id.radioOptionSad:
                mood = "SAD";
                icon = R.drawable.sad;
                break;
            case R.id.radioOptionIdk:
                mood = "OK";
                icon = R.drawable.neutral;
                break;
        }

        return new Note(title, mood, description, icon);
    }
}