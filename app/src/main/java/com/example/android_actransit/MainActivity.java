package com.example.android_actransit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ArrayList<StopsModel> model;
    private ProgressBar progressBar;
    private StopsAsyncTask stopsAsyncTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        model = new ArrayList<>();

        new StopsAsyncTask(MainActivity.this, recyclerView, progressBar, model).execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
    }
}