package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dishant on 24/3/17.
 */

public class CircularAdapter extends RecyclerView.Adapter<CircularAdapter.ViewHolder> {

    List<SearchData> data = Collections.EMPTY_LIST;
    Context context;
    private LayoutInflater layoutInflater;

    public CircularAdapter(Context context, List<SearchData> data){

        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.circular_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SearchData current = data.get(position);

        holder.circleImageView.setImageResource(current.imgId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView = (CircleImageView) itemView.findViewById(R.id.circleImage);

        }
    }
}
