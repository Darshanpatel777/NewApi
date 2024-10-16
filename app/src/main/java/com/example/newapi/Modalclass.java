package com.example.newapi;

import java.io.Serializable;

public class Modalclass implements Serializable {


    String id;
    String title;
    String date;
    String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Modalclass(String id, String title, String date, String description)
    {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }




}
