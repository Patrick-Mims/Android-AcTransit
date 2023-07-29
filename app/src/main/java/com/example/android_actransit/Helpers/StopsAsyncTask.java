package com.example.android_actransit.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Adapters.StopsAdapter;
import com.example.android_actransit.Models.StopsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class StopsAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<StopsModel> model;

    private int PROGRESS_MAX;
    public StopsAsyncTask(Context context, RecyclerView recyclerView, ProgressBar progressBar, ArrayList<StopsModel> model) {
        this.model = model;
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        String results = null;
        StringBuilder builder;
        URL url;

        Log.i("PARAMS", params[0]);

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

    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONArray list = new JSONArray(result);

            for(int i = 0; i < list.length(); i++) {
                model.add(new StopsModel(
                        list.getJSONObject(i).getInt("StopId"),
                        list.getJSONObject(i).getString("Name"),
                        list.getJSONObject(i).getDouble("Latitude"),
                        list.getJSONObject(i).getDouble("Longitude"),
                        list.getJSONObject(i).getString("ScheduledTime")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setAdapter(new StopsAdapter(model));
    }
}