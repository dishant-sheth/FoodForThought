package com.example.dishant.navigationdrawer;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dishant on 9/3/17.
 */

public class Download extends AsyncTask<String, Void, String>{

    URL url = null;
    HttpURLConnection httpURLConnection = null;
    StringBuilder result;

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

            while ((current = reader.readLine()) != null){
                result.append(current);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return result.toString();
    }
}
