package com.example.thelast.interfaces;

import com.example.thelast.ownmodal.Album;

import java.util.ArrayList;

public interface Albums {
    void onStart();
    void onEnd(ArrayList<Album> albums);
}