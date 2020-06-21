package com.marvelquiz.bean.comics;

import java.util.ArrayList;

import com.marvelquiz.bean.Thumbnail;

public class Comic {
    private int id;
    private String title;
    private ArrayList<ComicDate> date;
    private Thumbnail thumbnail;
    private Creators creators;

    public int getId() {
        return id;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public ArrayList<ComicDate> getDate() {
        return date;
    }

    public void setDate(ArrayList<ComicDate> date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setId(int id) {
        this.id = id;
    }
}