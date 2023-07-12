package com.example.android_actransit.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Adapters.StopsAdapter;
import com.example.android_actransit.MainActivity;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class StopsAsyncTask extends AsyncTask<String, Integer, String> {
    public ProgressBar progressBar;
    public Context context;

    RecyclerView rv;

    public ArrayList<StopsModel> model;
    public StopsAsyncTask(Context context, ArrayList<StopsModel> model) {
        this.context = context;
        this.model = model;
    }

    protected void onPreExecute() {
       // progressBar.setVisibility(View.VISIBLE);
    }

    protected String doInBackground(String... params) {
        int count = 100;
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        String results;
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

            for(int i = 0; i < count; i++) {
               publishProgress(i);
            }

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

        //rv = rv.findViewById(R.id.recyclerView);

        try {
            // model = new ArrayList<>();

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
    }
}