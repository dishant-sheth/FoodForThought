package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dishant on 7/3/17.
 */

public class HorizontalCardAdapter extends RecyclerView.Adapter<HorizontalCardAdapter.HzViewHolder> {

    Context context;
    List<CardData> data = Collections.EMPTY_LIST;
    LayoutInflater layoutInflater;

    public interface OnItemClickListener {

        void onItemClick(CardData info);

    }

    private final OnItemClickListener listener;

    public HorizontalCardAdapter(Context context, List<CardData> data, OnItemClickListener listener){

        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.listener = listener;

    }


    @Override
    public HzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.horizontalcard, parent, false);
        HzViewHolder hzViewHolder = new HzViewHolder(view);
        return hzViewHolder;
    }

    @Override
    public void onBindViewHolder(HzViewHolder holder, int position) {

        CardData current = data.get(position);
        holder.bind(current, listener);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HzViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public HzViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.horTitle);
            imageView = (ImageView) itemView.findViewById(R.id.horIcon);

        }

        public void bind(final CardData info, final HorizontalCardAdapter.OnItemClickListener listener) {

            textView.setText(info.recName);

            Log.i("name", info.recName);

            Picasso.with(context)
                    .load(info.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .resize(400,400)
                    .into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });


        }
    }
}
