package com.example.thelast.ownmodal;

import java.io.Serializable;

public class Album implements Serializable{

    private String id,name, image, thumb, artist = "";

    public Album(String id, String name, String image, String thumb) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.thumb = thumb;
    }

    public Album(String id, String name, String image, String thumb, String artist) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.thumb = thumb;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getThumb() {
        return thumb;
    }

    public String getArtist() {
        return artist;
    }
}