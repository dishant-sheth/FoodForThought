package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dishant on 21/3/17.
 */

public class IngreAdapter extends RecyclerView.Adapter<IngreAdapter.ViewHolder> {

    private static final String LOG_TAG = IngreAdapter.class.getSimpleName();
    private Context context;
    private List<IngreData> data = Collections.EMPTY_LIST;



    private MultipleSelectAdapterCallback mMultipleSelectAdapterCallback;

    public IngreAdapter(Context context, List<IngreData> data){

        this.context = context;
        this.data = data;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ingreImage;
        TextView ingreName;
        TextView ingreInfo;
        public ImageButton expand;
        LinearLayout ingreDetails;

        public ViewHolder(View itemView) {
            super(itemView);

            ingreName = (TextView) itemView.findViewById(R.id.ingreName);
            ingreImage = (ImageView) itemView.findViewById(R.id.ingreImage);
            ingreInfo = (TextView) itemView.findViewById(R.id.ingreInfo);
            expand = (ImageButton) itemView.findViewById(R.id.extend_button);
            ingreDetails = (LinearLayout) itemView.findViewById(R.id.ingreDetailsLayout);

            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(getAdapterPosition() != RecyclerView.NO_POSITION){

                        if(data.get(getAdapterPosition()).ismActivateExpansion()){
                            data.get(getAdapterPosition()).setmActivateExpansion(false);
                        }
                        else {
                            data.get(getAdapterPosition()).setmActivateExpansion(true);
                        }
                            notifyItemChanged(getAdapterPosition());
                    }

                }
            });

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Log.d(LOG_TAG, "Clicked");

            if (mMultipleSelectAdapterCallback != null && getAdapterPosition() != RecyclerView.NO_POSITION) {

                int clickedPosition = getAdapterPosition();
                IngreData clickedItem = data.get(clickedPosition);

                if (clickedItem.ismChecked()) {
                    clickedItem.setmChecked(false);
                } else {
                    clickedItem.setmChecked(true);
                }

                notifyItemChanged(clickedPosition);

                SelectedItem selectedItem = getSelectedItem();
                Log.d(LOG_TAG, "count selected item: " + selectedItem.getCount() + " selected item ID list: " + selectedItem.getSelectedItemIds());


                mMultipleSelectAdapterCallback.itemClicked(selectedItem.getCount(), selectedItem.getSelectedItemIds());

            }

//            mExpandButton.setBackground(ResourceUtil.acquireDrawable(mContext, R.color.pink_500));
//            mPrimaryTextView.setBackground(ResourceUtil.acquireDrawable(mContext, R.color.blue_500));


        }
    }

    private SelectedItem getSelectedItem(){
        SelectedItem selectedItem = new SelectedItem();

        int counter = 0;
        if(data != null){
            for(IngreData ingd : data){
                if(ingd.ismChecked()){
                    counter++;
                    selectedItem.addItems(ingd.getmId());
                }
            }
        }

        selectedItem.setCount(counter);
        return selectedItem;
    }

    public interface MultipleSelectAdapterCallback {
        public void itemClicked(int count, List<Integer> selectedItemId);

    }

    public MultipleSelectAdapterCallback getMultipleSelectAdapterCallback() {
        return mMultipleSelectAdapterCallback;
    }

    public void setMultipleSelectAdapterCallback(MultipleSelectAdapterCallback multipleSelectAdapterCallback) {
        mMultipleSelectAdapterCallback = multipleSelectAdapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(LOG_TAG, "On create view holder" + viewType);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingre_card, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(context)
                .load(data.get(position).getIngreImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(400,400)
                .into(holder.ingreImage);

        holder.ingreName.setText(data.get(position).getIngreName());
        holder.ingreInfo.setText(data.get(position).getSecondaryText());

        if(data.get(position).ismChecked()){
            holder.ingreDetails.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        else{
            holder.ingreDetails.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        //expand supporting textView

        if(data.get(position).ismActivateExpansion()){
            holder.ingreInfo.setVisibility(View.VISIBLE);
        }
        else {
            holder.ingreInfo.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        else {
            return data.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    //Class to return # of selected items and their ids

    private class SelectedItem {

        private int mCount;
        private List<Integer> mSelectedItemIds = new ArrayList<>();

        private void addItems(int id){
            mSelectedItemIds.add(id);
        }

        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            mCount = count;
        }

        public List<Integer> getSelectedItemIds() {
            return mSelectedItemIds;
        }


    }

}
