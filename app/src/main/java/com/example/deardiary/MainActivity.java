package com.example.deardiary;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.deardiary.Models.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Note> list;
    ArrayAdapter<Note> arrayAdapter;
    ListView listView;
    static int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupListView();
        setupDeleteByItemLongClick();
        setupCreateButton();
        setupNoteDetailByItemClick();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    public void setupNoteDetailByItemClick(){

        ActivityResultLauncher<Intent> detailActivityResulLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Note updatedNote = (Note) data.getSerializableExtra("note");
                            list.set(selectedPosition, updatedNote);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("note", list.get(position));
                selectedPosition = position;
                detailActivityResulLauncher.launch(intent);
            }
        });
    }

    public void setupListView(){
        listView = findViewById(R.id.ListView);
        list = new ArrayList<>();
        list.add(new Note("note1", "HAPPY", "today i walked my dog", 1));
        list.add(new Note("note2", "SAD", "today i did not walk my dog", 1));
        list.add(new Note("note3", "HAPPY", "today i walked my dog", 1));
        list.add(new Note("note4", "HAPPY", "today i walked my dog", 1));
        list.add(new Note("note5", "HAPPY", "today i walked my dog", 1));
        list.add(new Note("note6", "HAPPY", "today i walked my dog", 1));

        arrayAdapter = new ArrayAdapter<Note>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
    }

    public void setupDeleteByItemLongClick(){

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete note");
                alert.setMessage("Are you sure you want to delete this note?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        list.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    public void setupCreateButton(){
        ActivityResultLauncher<Intent> createActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Note newNote = (Note) data.getSerializableExtra("note");
                            list.add(newNote);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                });

        ImageButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                createActivityResultLauncher.launch(intent);
            }
        });
    }
}