package com.example.deardiary;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.ListView);
        List<Note> list = new ArrayList<>();
        list.add(new Note("title1", "lala", "vfujdegwvkc", 1));
        list.add(new Note("title2", "lala", "vfujdegwvkc", 1));
        list.add(new Note("pisici", "lala", "vfujdegwvkc", 1));
        list.add(new Note("catei", "lala", "vfujdegwvkc", 1));
        list.add(new Note("tit", "012461", "vfujdegwvkc", 1));
        list.add(new Note("tit2", "lala", "vfujdegwvkc", 1));

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, NoteDetailActivity.class);
                i.putExtra("note", list.get(position));
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        someActivityResultLauncher = registerForActivityResult(
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
                someActivityResultLauncher.launch(intent);
            }
        });

    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}