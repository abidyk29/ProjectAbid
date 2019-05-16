package com.example.thelast.interfaces;

import com.example.thelast.ownmodal.ItemSong;

import java.util.ArrayList;

public interface SongListener {
    void onStart();
    void onEnd(String success, ArrayList<ItemSong> arrayList);
}