package com.marvelquiz.bean.comics;

import java.util.ArrayList;

public class ResultsComics {
    private int id;
    private String title;
    private ArrayList<DateComic> date;
    private ThumbnailComic thumbnail;

    public int getId() {
        return id;
    }

    public ThumbnailComic getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailComic thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<DateComic> getDate() {
        return date;
    }

    public void setDate(ArrayList<DateComic> date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

}