package com.example.thelast.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thelast.modal.ModalListSurat;

import java.util.List;

import supriyanto.tampilan.R;


public class AdapterListSurat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ModalListSurat> modalListSurats;

    public AdapterListSurat(Context context, List<ModalListSurat> modalListSurats) {
        this.context = context;
        this.modalListSurats = modalListSurats;
    }

    public class myAdapterListSurat extends RecyclerView.ViewHolder{

        private TextView ar, id, nomor, translate;
        private ImageView share;
        private MediaPlayer player;

        public myAdapterListSurat(View itemView) {
            super(itemView);

            ar = (TextView) itemView.findViewById(R.id.ar);
            id = (TextView) itemView.findViewById(R.id.id);
            nomor = (TextView) itemView.findViewById(R.id.nomor);
            translate = (TextView) itemView.findViewById(R.id.translate);
            share = (ImageView) itemView.findViewById(R.id.shares);
            player = new MediaPlayer();

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_surat, parent, false);

        return new myAdapterListSurat(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ModalListSurat mod = modalListSurats.get(position);

        ((myAdapterListSurat) holder).ar.setText(mod.getAr());
        ((myAdapterListSurat) holder).id.setText(mod.getId());
        ((myAdapterListSurat) holder).nomor.setText(mod.getNomor());
        ((myAdapterListSurat) holder).translate.setText(Html.fromHtml(mod.getTr()));

    }

    @Override
    public int getItemCount() {
        return modalListSurats.size();
    }
}