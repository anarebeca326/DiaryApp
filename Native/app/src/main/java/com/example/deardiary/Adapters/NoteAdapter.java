package com.example.deardiary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deardiary.Models.Note;
import com.example.deardiary.R;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    private Context mContext;
    private int mResource;

    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.itemIconView);
        TextView textView = convertView.findViewById(R.id.itemTitleView);

        imageView.setImageResource(getItem(position).getIcon());
        textView.setText(getItem(position).getTitle());

        return convertView;
    }
}
