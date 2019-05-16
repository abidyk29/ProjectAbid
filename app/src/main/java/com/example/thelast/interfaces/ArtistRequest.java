package com.example.thelast.interfaces;

import com.example.thelast.ownmodal.Artist;

import java.util.ArrayList;

public interface ArtistRequest {
    void onStart();
    void onEnd(ArrayList<Artist> artists);
}