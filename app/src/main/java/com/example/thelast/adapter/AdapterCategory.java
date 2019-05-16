package com.example.thelast.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thelast.ownmodal.Category;
import com.example.thelast.utils.Methods;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import supriyanto.tampilan.R;


public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Category> arrayList;
    private ArrayList<Category> filteredArrayList;
    private NameFilter filter;
    private int columnWidth = 0;

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public AdapterCategory(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.filteredArrayList = arrayList;
        Methods methods = new Methods(context);
        columnWidth = methods.getColumnWidth(3, 20);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RoundedImageView imageView;
        CardView cardView;
        LinearLayout ll;
        View vieww;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.tv_cat);
            imageView = view.findViewById(R.id.iv_cat);
            ll = view.findViewById(R.id.ll);
            vieww = view.findViewById(R.id.view_cat);
        }
    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private static ProgressBar progressBar;

        private ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cat, parent, false);
            return new MyViewHolder(itemView);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_progressbar, parent, false);
            return new ProgressViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {

//            ((MyViewHolder) holder).vieww.setLayoutParams(new FrameLayout.LayoutParams(columnWidth, columnWidth));
//            ((MyViewHolder) holder).imageView.setLayoutParams(new FrameLayout.LayoutParams(columnWidth, columnWidth));
//            ((MyViewHolder) holder).cardView.setLayoutParams(new LinearLayout.LayoutParams(columnWidth, columnWidth));
//            ((MyViewHolder) holder).cardView.setRadius(columnWidth / 2);
//            ((MyViewHolder) holder).imageView.setCornerRadius(columnWidth / 2);
            ((MyViewHolder) holder).textView.setText(arrayList.get(position).getName());
//            ((MyViewHolder) holder).imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get()
                    .load(arrayList.get(position).getImage())
                    .placeholder(R.drawable.placeholder_artist)
                    .into(((MyViewHolder) holder).imageView);
        } else {
            if (getItemCount() == 1) {
                ProgressViewHolder.progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    public void hideHeader() {
        ProgressViewHolder.progressBar.setVisibility(View.GONE);
    }

    public boolean isHeader(int position) {
        return arrayList.get(position) == null;
//        return position == arrayList.size();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(arrayList.get(position) != null) {
            return VIEW_ITEM;
        } else {
            return VIEW_PROG;
        }
//        return isHeader(position) ? VIEW_PROG : VIEW_ITEM;
    }

    public Category getItem(int pos) {
        return arrayList.get(pos);
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new NameFilter();
        }
        return filter;
    }

    private class NameFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint.toString().length() > 0) {
                ArrayList<Category> filteredItems = new ArrayList<>();

                for (int i = 0, l = filteredArrayList.size(); i < l; i++) {
                    String nameList = filteredArrayList.get(i).getName();
                    if (nameList.toLowerCase().contains(constraint))
                        filteredItems.add(filteredArrayList.get(i));
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            } else {
                synchronized (this) {
                    result.values = filteredArrayList;
                    result.count = filteredArrayList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            arrayList = (ArrayList<Category>) results.values;
            notifyDataSetChanged();
        }
    }
}