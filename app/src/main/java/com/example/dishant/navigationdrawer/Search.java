package com.example.dishant.navigationdrawer;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    LinearLayoutManager linearLayoutManager1;
    LinearLayoutManager linearLayoutManager2;
    LinearLayoutManager linearLayoutManager3;
    LinearLayoutManager linearLayoutManager4;
    ImageView ingreImage;
    ImageView cuisineImage;
    ImageView calorieImage;
    ImageView timeImage;
    ViewPager viewPager;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);



        viewPager = (ViewPager) view.findViewById(R.id.searchPager);

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(getActivity(), pagerData());

        viewPager.setAdapter(imageSliderAdapter);

        ingreImage = (ImageView) view.findViewById(R.id.ingredientImage);
        cuisineImage = (ImageView) view.findViewById(R.id.cuisineImage);
        calorieImage = (ImageView) view.findViewById(R.id.calorieImage);
        timeImage = (ImageView) view.findViewById(R.id.timeImage);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);

        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerView3);
        recyclerView3.setHasFixedSize(true);

        recyclerView4 = (RecyclerView) view.findViewById(R.id.recyclerView4);
        recyclerView4.setHasFixedSize(true);

        linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView4.setLayoutManager(linearLayoutManager4);


        CircularAdapter circularAdapter1 = new CircularAdapter(getActivity(), getData());
        CircularAdapter circularAdapter2 = new CircularAdapter(getActivity(), getData());
        CircularAdapter circularAdapter3 = new CircularAdapter(getActivity(), getData());
        CircularAdapter circularAdapter4 = new CircularAdapter(getActivity(), getData());

        recyclerView1.setAdapter(circularAdapter1);
        recyclerView2.setAdapter(circularAdapter2);
        recyclerView3.setAdapter(circularAdapter3);
        recyclerView4.setAdapter(circularAdapter4);

        ingreImage.setImageBitmap(decodeFromResource(getResources(), R.drawable.ingredients, 200, 200));
        cuisineImage.setImageBitmap(decodeFromResource(getResources(), R.drawable.cuisine, 200, 200));
        calorieImage.setImageBitmap(decodeFromResource(getResources(), R.drawable.calories, 200, 200));
        timeImage.setImageBitmap(decodeFromResource(getResources(), R.drawable.time, 200, 200));

        ingreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new IngredientsSearch();

                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        calorieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calories fragment = new Calories();

                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;

    }

    public List<SearchData> getData(){
        List<SearchData> data = new ArrayList<>();
        int icons = R.drawable.carrot;

        for(int i=0; i< 9; i++){
            SearchData current = new SearchData();
            current.imgId = icons;
            data.add(current);
        }

        return data;
    }

    public static Bitmap decodeFromResource(Resources res, int resId, int reqWidth, int reqHeight){


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    public static int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height>reqHeight || width>reqWidth){

            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while((halfHeight/inSampleSize)>reqHeight && (halfWidth/inSampleSize)>reqWidth){
                inSampleSize *= 2;
            }

        }
        return inSampleSize;

    }

    public Bitmap[] pagerData(){


        Bitmap one = decodeFromResource(getResources(), R.drawable.indianbg, 200, 200);
        Bitmap two = decodeFromResource(getResources(), R.drawable.chinesebg, 200, 200);
        Bitmap three = decodeFromResource(getResources(), R.drawable.italianbg, 200, 200);
        Bitmap four = decodeFromResource(getResources(), R.drawable.mexicanbg, 200, 200);
        Bitmap five = decodeFromResource(getResources(), R.drawable.americanbg, 200, 200);

        Bitmap[] imgs = {one, two, three, four, five};

        return imgs;
    }

}
