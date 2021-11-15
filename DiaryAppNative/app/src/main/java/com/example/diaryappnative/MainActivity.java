package com.example.diaryappnative;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.diaryappnative.Adapters.NoteAdapter;
import com.example.diaryappnative.Models.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> list;
    NoteAdapter noteAdapter;
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



    public void setupListView(){
        listView = findViewById(R.id.ListView);
        list = new ArrayList<>();
        list.add(new Note("note1", "HAPPY", "today i walked my dog", R.drawable.happy));
        list.add(new Note("note2", "SAD", "today i did not walk my dog", R.drawable.sad));
        list.add(new Note("note3", "OK", "today i walked my dog", R.drawable.neutral));
        list.add(new Note("note4", "HAPPY", "today i walked my dog", R.drawable.happy));
        list.add(new Note("note5", "OK", "today i walked my dog", R.drawable.neutral));
        list.add(new Note("note6", "HAPPY", "today i walked my dog", R.drawable.happy));

        noteAdapter = new NoteAdapter(this, R.layout.list_item, list);
        listView.setAdapter(noteAdapter);
    }

    public void setupNoteDetailByItemClick(){

        ActivityResultLauncher<Intent> detailActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Note updatedNote = (Note) data.getSerializableExtra("note");
                            list.set(selectedPosition, updatedNote);
                            noteAdapter.notifyDataSetChanged();
                        }
                    }
                });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("note", list.get(position));
                selectedPosition = position;
                detailActivityResultLauncher.launch(intent);
            }
        });
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
                        list.remove(position);
                        noteAdapter.notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
                            Intent data = result.getData();
                            Note newNote = (Note) data.getSerializableExtra("note");
                            list.add(newNote);
                            noteAdapter.notifyDataSetChanged();
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