package com.example.android_actransit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.android_actransit.Adapters.StopsAdapter;
import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    StopsAdapter adapter;
    ArrayList<StopsModel> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        items.add(new StopsModel(40001, "Lake Shore", 12.0, 32.0, "now"));

        getStops();

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);

        adapter = new StopsAdapter(MainActivity.this, items);

        recyclerView.setAdapter(adapter);
    }
    public void getStops() {
        new StopsAsyncTask(this, items).execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
    }
}