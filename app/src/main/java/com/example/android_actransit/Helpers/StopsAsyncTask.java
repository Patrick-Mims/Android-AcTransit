package com.example.android_actransit.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.android_actransit.Models.StopsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StopsAsyncTask extends AsyncTask<String, Void, String> {
    Context context;
    public ArrayList<StopsModel> stopsModel;

    public StopsAsyncTask(Context context, ArrayList<StopsModel> stopsModel) {
        this.context = context;
        this.stopsModel = stopsModel;
    }

    //@Override
    protected String doInBackground(String... params) {
        BufferedReader bufferedReader;
        HttpURLConnection urlConnection = null;
        String line;
        String results = null;
        StringBuilder builder;
        URL url;

        Log.i("JSON", params[0]);

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
                Log.i("LINE", line);
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
            JSONArray list = new JSONArray(result);
            JSONObject jsonObject;

            for(int i = 0; i < list.length(); i++) {
                jsonObject = list.getJSONObject(i);
                Log.i("DATA", jsonObject.getString("Name") + " | " +  jsonObject.getString("StopId") + " | " + jsonObject.getString("Latitude") );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}