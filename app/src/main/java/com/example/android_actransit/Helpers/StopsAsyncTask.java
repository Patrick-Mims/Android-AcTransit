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
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Context context;
    ArrayList<StopsModel> model;

    //public StopsAsyncTask(ArrayList<StopsModel> model) {
    private int PROGRESS_MAX;
    public StopsAsyncTask(Context context, RecyclerView recyclerView, ProgressBar progressBar, ArrayList<StopsModel> model) {
        this.model = model;
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        // this.PROGRESS_MAX = this.progressBar.getMax();
    }

    protected void onPreExecute() {
       super.onPreExecute();
       // this.progressBar.setVisibility(ProgressBar.VISIBLE);
       //progressDialog = new ProgressDialog(context);
    }

    protected String doInBackground(String... params) {

        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        // ArrayList<StopsModel> results = null;
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
/*
            for(int i = 0; i < count; i++) {
               publishProgress(i);
            }

 */
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

      //  progressDialog.dismiss();

//        this.model.add(new StopsModel(10000, "Templeton Ave #5", 33.99, 11.77, "Patrick"));

        //StopsAdapter adapter = new StopsAdapter(context, result);

        try {
            JSONArray list = new JSONArray(result);
            JSONObject jsonObject;

            for(int i = 0; i < list.length(); i++) {
                jsonObject = list.getJSONObject(i);
                model.add(new StopsModel(jsonObject.getInt("StopId"), jsonObject.getString("Name") ,jsonObject.getDouble("Latitude"), jsonObject.getDouble("Longitude"), jsonObject.getString("ScheduledTime")));
                Log.i("RESULT", jsonObject.getInt("StopId") + " | " + jsonObject.getString("Name") + " | " + jsonObject.getString("Latitude") + " | " +
                        jsonObject.getString("Longitude") + " | " + jsonObject.getString("ScheduledTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        StopsAdapter adapter = new StopsAdapter(context, model);
        this.recyclerView.setAdapter(adapter);

        for(StopsModel sm: this.model){
            Log.i("VIDEO-100->", sm.getName());
        }
    }
}