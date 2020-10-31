package com.example.youtube;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.Viewholder>{

   List<Youtube_data> list;
   Context mContext;

    public YoutubeAdapter(List<Youtube_data> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
     Glide.with(mContext).load(list.get(position).getThumbnails()).into(holder.thumbnail);
     holder.title.setText(list.get(position).getTitle());
     holder.channeltitle.setText(list.get(position).channelTitle);
     holder.time.setText(list.get(position).publishTime);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        ImageView thumbnail;
        TextView title;
        TextView channeltitle,time;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            thumbnail=(ImageView) itemView.findViewById(R.id.thumbnail);
            title=(TextView) itemView.findViewById(R.id.title);
            channeltitle=(TextView) itemView.findViewById(R.id.channelTitle);
            time=(TextView) itemView.findViewById(R.id.publishtime);

        }
    }


}
