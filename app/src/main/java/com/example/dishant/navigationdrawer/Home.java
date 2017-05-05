package com.example.dishant.navigationdrawer;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    RecyclerView recyclerView5;
    LinearLayoutManager linearLayoutManager1;
    LinearLayoutManager linearLayoutManager2;
    LinearLayoutManager linearLayoutManager3;
    LinearLayoutManager linearLayoutManager4;
    LinearLayoutManager linearLayoutManager5;
    ImageView india, italy, china, america, mexico;
    LinearLayout indiaLayout, italyLayout, chinaLayout, americaLayout, mexicoLayout;
    List<CardData> data;


    HorizontalCardAdapter horizontalCardAdapter1;
    HorizontalCardAdapter horizontalCardAdapter2;
    HorizontalCardAdapter horizontalCardAdapter3;
    HorizontalCardAdapter horizontalCardAdapter4;
    HorizontalCardAdapter horizontalCardAdapter5;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.india);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.italy);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.china);
        recyclerView4 = (RecyclerView) view.findViewById(R.id.america);
        recyclerView5 = (RecyclerView) view.findViewById(R.id.mexico);

        data = new ArrayList<>();

        india = (ImageView) view.findViewById(R.id.indiaImage);
        italy = (ImageView) view.findViewById(R.id.italyImage);
        china = (ImageView) view.findViewById(R.id.chinaImage);
        america = (ImageView) view.findViewById(R.id.americaImage);
        mexico = (ImageView) view.findViewById(R.id.mexicoImage);

        indiaLayout = (LinearLayout) view.findViewById(R.id.indiaLayout);
        italyLayout = (LinearLayout) view.findViewById(R.id.italyLayout);
        chinaLayout = (LinearLayout) view.findViewById(R.id.chinaLayout);
        americaLayout = (LinearLayout) view.findViewById(R.id.americaLayout);
        mexicoLayout = (LinearLayout) view.findViewById(R.id.mexicoLayout);


        india.setImageBitmap(decodeFromResource(getResources(), R.drawable.indianbg, 200, 200));
        italy.setImageBitmap(decodeFromResource(getResources(), R.drawable.italianbg, 200, 200));
        china.setImageBitmap(decodeFromResource(getResources(), R.drawable.chinesebg, 200,200));
        america.setImageBitmap(decodeFromResource(getResources(), R.drawable.americanbg, 200, 200));
        mexico.setImageBitmap(decodeFromResource(getResources(), R.drawable.mexicanbg, 200, 200));

        linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager1.setReverseLayout(true);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager2.setReverseLayout(true);
        linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager3.setReverseLayout(true);
        linearLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager4.setReverseLayout(true);
        linearLayoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager5.setReverseLayout(true);


        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        recyclerView4.setLayoutManager(linearLayoutManager4);
        recyclerView5.setLayoutManager(linearLayoutManager5);


        horizontalCardAdapter1 = new HorizontalCardAdapter(getActivity(), data, new HorizontalCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardData info) {
                String recName = info.recName;
                Intent intent = new Intent(getActivity(), Second.class);
                intent.putExtra("name", recName);
                startActivity(intent);
            }
        });
        horizontalCardAdapter2 = new HorizontalCardAdapter(getActivity(), data, new HorizontalCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardData info) {
                String recName = info.recName;
                Intent intent = new Intent(getActivity(), Second.class);
                intent.putExtra("name", recName);
                startActivity(intent);
            }
        });
        horizontalCardAdapter3 = new HorizontalCardAdapter(getActivity(), data, new HorizontalCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardData info) {
                String recName = info.recName;
                Intent intent = new Intent(getActivity(), Second.class);
                intent.putExtra("name", recName);
                startActivity(intent);
            }
        });
        horizontalCardAdapter4 = new HorizontalCardAdapter(getActivity(), data, new HorizontalCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardData info) {
                String recName = info.recName;
                Intent intent = new Intent(getActivity(), Second.class);
                intent.putExtra("name", recName);
                startActivity(intent);
            }
        });
        horizontalCardAdapter5 = new HorizontalCardAdapter(getActivity(), data, new HorizontalCardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CardData info) {
                String recName = info.recName;
                Intent intent = new Intent(getActivity(), Second.class);
                intent.putExtra("name", recName);
                startActivity(intent);
            }
        });

        recyclerView1.setAdapter(horizontalCardAdapter1);
        recyclerView2.setAdapter(horizontalCardAdapter2);
        recyclerView3.setAdapter(horizontalCardAdapter3);
        recyclerView4.setAdapter(horizontalCardAdapter4);
        recyclerView5.setAdapter(horizontalCardAdapter5);

        new DownloadData().execute("https://warm-caverns-39626.herokuapp.com/api/recipes");



        return view;

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

    public class DownloadData extends AsyncTask<String, Void, String>  {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        StringBuilder result;

        @Override
        protected void onPreExecute() {

            progressDialog.show();
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_screen);

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... urls) {


            result = new StringBuilder();


            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String current = "";

                    while ((current = reader.readLine()) != null) {
                        result.append(current);
                    }

                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }

            Log.i("result", result.toString() );
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            try {

                JSONArray jsonArray = new JSONArray(s);

                for (int i = 0; i < jsonArray.length() - 1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    CardData cardData = new CardData();

                    cardData.recName = jsonObject.getString("name");
                    cardData.image = jsonObject.getString("image");
                    Log.i("done", "done");

                    data.add(cardData);

                }

                horizontalCardAdapter1.notifyDataSetChanged();
                horizontalCardAdapter2.notifyDataSetChanged();
                horizontalCardAdapter3.notifyDataSetChanged();
                horizontalCardAdapter4.notifyDataSetChanged();
                horizontalCardAdapter5.notifyDataSetChanged();
                Log.i("notifies", "adapter notified");




            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }






}
