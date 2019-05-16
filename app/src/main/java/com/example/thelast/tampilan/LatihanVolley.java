package com.example.thelast.tampilan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import supriyanto.tampilan.R;

public class LatihanVolley extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        requestMeth();
    }

    private void requestMeth() {

        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, "http://192.168.5.82/mp3quran/api.php?video&page=1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("hasilbos", response + "");
                try {
                    JSONArray array = response.getJSONArray("ONLINE_MP3");
                    for (int i = 0; i<array.length(); i++){

                        JSONObject obj = array.getJSONObject(i);
                        String datevid = obj.getString("datevid");

                        Log.d("datevid", datevid);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BOSS", error + "");
            }
        });
        queue.add(obj);
    }
}