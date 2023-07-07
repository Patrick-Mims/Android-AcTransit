package com.example.android_actransit.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.android_actransit.Models.StopsModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class StopsAsyncTask extends AsyncTask<String, Void, String> {
    public Context context;
    public ArrayList<StopsModel> model;
    public StopsAsyncTask(Context context, ArrayList<StopsModel> model) {
        this.context = context;
        this.model = model;
    }

    protected String doInBackground(String... params) {
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        String results = null;
        StringBuilder builder;
        URL url;

        Log.i("PARAM", params[0]);

        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();

            if(code != 200) {
                throw new IOException("Invalid response from server: " + code);
            }

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            builder = new StringBuilder();

            while((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            if(builder.length() == 0) {
                return null;
            }

            results = builder.toString();

            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            model = new ArrayList<>();

            JSONArray list = new JSONArray(result);
            JSONObject jsonObject;

            for(int i = 0; i < list.length(); i++) {
                jsonObject = list.getJSONObject(i);
                Log.i("RESULT", jsonObject.getInt("StopId") + " | " + jsonObject.getString("Name") + " | " + jsonObject.getString("Latitude") + " | " +
                        jsonObject.getString("Longitude") + " | " + jsonObject.getString("ScheduledTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}