package com.example.android_actransit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android_actransit.Adapters.StopsAdapter;
import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R.id;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
//    LinearLayoutManager layoutManager;

    ArrayList<StopsModel> model;
    private ProgressBar progressBar;
    private StopsAsyncTask stopsAsyncTask;
    private RecyclerView recyclerView;

    private ArrayList<StopsModel> initData() {
        ArrayList<StopsModel> items = new ArrayList<>();
        /*
        items.add(new StopsModel(40001, "Lake Shore", 12.0, 32.0, "now"));
        items.add(new StopsModel(53001, "Grand Ave", 72.0, 22.0, "Later"));
        items.add(new StopsModel(63333, "Cleveland Ave", 72.0, 22.0, "Sometime Today"));

         */
        //return objects;
        return items;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        model = initData();

        new StopsAsyncTask(MainActivity.this, recyclerView, progressBar, model).execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
//       ArrayList<StopsModel> list = new ArrayList<>();

        /*
        StopsAsyncTask stopsAsyncTask = new StopsAsyncTask(MainActivity.this, list);
        stopsAsyncTask.execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
         */


        /*
        for(StopsModel sm: list){
            Log.i("VIDEO-1000", sm.getName());
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        StopsAdapter adapter = new StopsAdapter(MainActivity.this, list);

        recyclerView.setAdapter(adapter);
        */
    }
}