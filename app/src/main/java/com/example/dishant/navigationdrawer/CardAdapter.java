package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dishant on 27/2/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.myViewHolder> {

    public interface OnItemClickListener {

        void onItemClick(RecInfo info);

    }

    List<RecInfo> recipes;
    LayoutInflater layoutInflater;
    private final OnItemClickListener listener;

    public CardAdapter(Context context, List<RecInfo> recipes, OnItemClickListener listener){

        layoutInflater = LayoutInflater.from(context);
        this.recipes = recipes;
        this.listener = listener;

    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        myViewHolder viewHolder = new myViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        RecInfo current = recipes.get(position);
         holder.bind(current, listener);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public myViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.recImage);
            name = (TextView) itemView.findViewById(R.id.recName);
        }

        public void bind(final RecInfo info, final OnItemClickListener listener){

            name.setText(info.recipe_name);
            image.setImageResource(info.recipe_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });

        }

    }
}
