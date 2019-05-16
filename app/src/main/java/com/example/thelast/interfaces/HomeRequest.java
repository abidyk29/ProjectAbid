package com.example.thelast.interfaces;

import com.example.thelast.ownmodal.Album;
import com.example.thelast.ownmodal.Artist;
import com.example.thelast.ownmodal.Category;
import com.example.thelast.ownmodal.ItemSong;
import com.example.thelast.ownmodal.MisharyRashid;
import com.example.thelast.ownmodal.MuhammadThaha;

import java.util.ArrayList;

public interface HomeRequest {
    void onStart();
    void onEnd(String success, ArrayList<Category> categories, ArrayList<Album> albums, ArrayList<Artist> artists, ArrayList<ItemSong> itemSongs);
}