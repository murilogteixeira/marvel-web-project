package com.marvelquiz.bean.character;

import com.marvelquiz.bean.Thumbnail;

public class Character {
    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    public int getId() {
        return id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}