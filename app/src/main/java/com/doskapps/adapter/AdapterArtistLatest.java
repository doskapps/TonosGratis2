package com.doskapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doskapps.item.ItemArtist;
import com.doskapps.tonosgratis.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterArtistLatest extends RecyclerView.Adapter<AdapterArtistLatest.MyViewHolder> {

    private Context context;
    private ArrayList<ItemArtist> arrayList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView_artist, imageView;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView_latest_list);
            imageView_artist = view.findViewById(R.id.imageView_latest_list);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    public AdapterArtistLatest(Context context, ArrayList<ItemArtist> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recent, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.imageView.setVisibility(View.GONE);
        holder.textView.setText(arrayList.get(position).getName());
        Picasso.get()
                .load(arrayList.get(position).getImage())
                .into(holder.imageView_artist);

    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}