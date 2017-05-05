package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class recAdapter extends RecyclerView.Adapter<recAdapter.myViewHolder> {

  public interface OnItemClickListener {

    void onItemClick(Info info);

  }

  List<Info> infos;
  LayoutInflater layoutInflater;
  private final OnItemClickListener listener;

  public recAdapter(Context context, List<Info> infos, OnItemClickListener listener) {

    layoutInflater = LayoutInflater.from(context);
    this.infos = infos;
    this.listener = listener;

  }


  @Override
  public recAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
    myViewHolder viewHolder = new myViewHolder(view);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(myViewHolder holder, int position) {

    Info current = infos.get(position);
    holder.bind(current, listener);

  }


  @Override
  public int getItemCount() {
    return infos.size();
  }

  public class myViewHolder extends RecyclerView.ViewHolder {

    CircleImageView image;
    TextView name;

    public myViewHolder(View itemView) {
      super(itemView);
      image = (CircleImageView) itemView.findViewById(R.id.icon);
      name = (TextView) itemView.findViewById(R.id.title);
    }

    public void bind(final Info info, final recAdapter.OnItemClickListener listener) {

      name.setText(info.name);
      image.setImageResource(info.img_id);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          listener.onItemClick(info);
        }
      });

    }

  }
}