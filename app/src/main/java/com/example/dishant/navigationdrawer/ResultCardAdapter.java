package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by dishant on 17/4/17.
 */

public class ResultCardAdapter extends RecyclerView.Adapter<ResultCardAdapter.MyViewHolder> {

    Context context;
    List<ResultCardData> data = Collections.EMPTY_LIST;
    LayoutInflater layoutInflater;

    public interface OnItemClickListener{
        void onItemClick(ResultCardData info);
    }

    private final OnItemClickListener listener;

    public ResultCardAdapter(Context context, List<ResultCardData> data, OnItemClickListener listener){

        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.listener = listener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.result_card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ResultCardData current = data.get(position);
        holder.bind(current, listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name, time, cals;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.rec_image);
            name = (TextView) itemView.findViewById(R.id.rec_name);
            time = (TextView) itemView.findViewById(R.id.rec_time);
            cals = (TextView) itemView.findViewById(R.id.rec_calorie);
        }

        public void bind(final ResultCardData info, final ResultCardAdapter.OnItemClickListener listener){

            name.setText(info.rec_name);
            time.setText(info.rec_time);
            cals.setText(info.rec_calories);

            Picasso.with(context)
                    .load(info.rec_image)
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
