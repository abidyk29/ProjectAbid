package com.example.thelast.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thelast.ownmodal.Artist;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import supriyanto.tampilan.R;



public class AdapterArtist extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<Artist> artists;
    Context context;

    public AdapterArtist(ArrayList<Artist> artists, Context context) {
        this.artists = artists;
        this.context = context;
    }

    public class myArtistAdapter extends RecyclerView.ViewHolder{

        private RoundedImageView image;
        private TextView title;
        private Typeface typeface;

        public myArtistAdapter(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            //typeface = typeface.createFromAsset(context.getAssets(), "proximanova-regular.otf");
            title.setTypeface(typeface);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_artist, parent, false);

        return new myArtistAdapter(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Artist artist = artists.get(position);
        Picasso.get().load(artist.getThumb()).into(((myArtistAdapter) holder).image);
        ((myArtistAdapter) holder).title.setText(artist.getName());

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}