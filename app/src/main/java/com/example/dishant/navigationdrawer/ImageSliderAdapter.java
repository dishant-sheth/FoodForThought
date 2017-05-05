package com.example.dishant.navigationdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dishant on 24/3/17.
 */

public class ImageSliderAdapter extends PagerAdapter {



    private LayoutInflater layoutInflater;
    Bitmap[] imgs = new Bitmap[5];

    public ImageSliderAdapter(Context context, Bitmap[] imgs) {
        layoutInflater = LayoutInflater.from(context);
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.pager_view, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.pagerImage);
        imageView.setImageBitmap(imgs[position]);
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.invalidate();

    }
}
