package com.example.deardiary.Models;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    public String title;
    public Date date_created;
    public Date date_last_edited;
    public String mood;
    public String description;
    public int icon;


    public Note(String title, String mood, String description, int icon) {
        this.title = title;
        this.date_created = java.util.Calendar.getInstance().getTime();
        this.date_last_edited = this.date_created;
        this.mood = mood;
        this.description = description;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_last_edited() {
        return date_last_edited;
    }

    public void setDate_last_edited(Date date_last_edited) {
        this.date_last_edited = date_last_edited;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return title;
    }
}
