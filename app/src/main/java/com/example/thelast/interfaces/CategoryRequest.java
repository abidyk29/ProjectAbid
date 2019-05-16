package com.example.thelast.interfaces;

import com.example.thelast.ownmodal.Category;

import java.util.ArrayList;

public interface CategoryRequest {
    void onStart();
    void onEndt(String success, ArrayList<Category> categories);
}