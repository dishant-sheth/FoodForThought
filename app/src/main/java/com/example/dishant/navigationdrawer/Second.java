package com.example.dishant.navigationdrawer;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Second extends AppCompatActivity {

    private Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView favourite, share, cuisine, time, calories, recipe_image;
    TextView nameText, descText, cuisineText, timeText, calorieText, methodText;
    LinearLayout linearLayout;
    ArrayList<String> ingre;

    String mName, mImage, mCuisine, mDesc, mMethod, receivedName;
    int mCalories, mTime, mNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        receivedName = getIntent().getStringExtra("name");

        linearLayout = (LinearLayout) findViewById(R.id.ingredient_layout);
        ingre = new ArrayList<>();

        recipe_image = (ImageView) findViewById(R.id.recipe_image);
        favourite = (ImageView) findViewById(R.id.favourite);
        share = (ImageView) findViewById(R.id.share);
        cuisine = (ImageView) findViewById(R.id.cui);
        time = (ImageView) findViewById(R.id.time);
        calories = (ImageView) findViewById(R.id.calorie);

        nameText = (TextView) findViewById(R.id.recipe_name);
        cuisineText = (TextView) findViewById(R.id.recipe_cuisine);
        timeText = (TextView) findViewById(R.id.recipe_time);
        calorieText = (TextView) findViewById(R.id.recipe_calorie);
        methodText = (TextView) findViewById(R.id.recipe_method);
        descText = (TextView) findViewById(R.id.recipe_desc);

        String rec_url = "https://warm-caverns-39626.herokuapp.com/api/recipes/name:"+receivedName;
        rec_url = rec_url.replaceAll(" ", "%20");

        new DownloadData().execute(rec_url);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getApplicationContext(), R.color.cardview_light_background));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.cardview_shadow_end_color));


        favourite.setImageBitmap(decodeFromResource(getResources(), R.drawable.heart, 50, 50 ));
        share.setImageBitmap(decodeFromResource(getResources(), R.drawable.ic_share, 40, 40 ));
        cuisine.setImageBitmap(decodeFromResource(getResources(), R.drawable.cuisineicon, 30, 30 ));
        time.setImageBitmap(decodeFromResource(getResources(), R.drawable.clockicon, 30, 30 ));
        calories.setImageBitmap(decodeFromResource(getResources(), R.drawable.caloriesicon, 30, 30 ));




    }

    public void setViews(){

        nameText.setText(mName);
        descText.setText(mDesc);
        cuisineText.setText(mCuisine);
        timeText.setText(String.valueOf(mTime) + " minutes");
        calorieText.setText(String.valueOf(mCalories) + " kcal");
        methodText.setText(mMethod);

        collapsingToolbarLayout.setTitle(mName.toUpperCase());

        Picasso.with(getApplicationContext())
                .load(mImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(400,400)
                .into(recipe_image);


        for(int i=0; i<mNo; i++){
            String in = ingre.get(i);

            TextView textView = new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(in);
            textView.setTextSize(18);
            linearLayout.addView(textView);

        }

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


    public class DownloadData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog = new ProgressDialog(Second.this);
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



            try {

                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                mName = jsonObject.getString("name");
                mImage = jsonObject.getString("image");
                mCuisine = jsonObject.getString("cuisine");
                mDesc = jsonObject.getString("desc");
                mCalories = jsonObject.getInt("calories");
                mTime = jsonObject.getInt("time");
                mNo = jsonObject.getInt("no");
                mMethod = jsonObject.getString("method");

                JSONArray ingreArray = jsonObject.getJSONArray("ingredients");
                JSONArray quanArray = jsonObject.getJSONArray("quantity");

                for(int i=0; i<mNo; i++){
                    String ing = ingreArray.getString(i);
                    String qua = quanArray.getString(i);

                    ingre.add(ing + " - " + qua);
                }

                progressDialog.dismiss();

                setViews();



            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }



}
