package com.example.android_actransit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_actransit.Adapters.StopsAdapter;
import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView resultsTextView;
    ArrayList<StopsModel> stopsModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsTextView = findViewById(R.id.textView);

        RecyclerView recyclerView = findViewById(R.id.stops_recycler);

        Button stopButton = findViewById(R.id.button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStops();
            }
        });

//        StopsAdapter adapter = new StopsAdapter(MainActivity.this, stopsModels);
//        recyclerView.setAdapter(adapter);

        // stopsModels.add(new StopsModel(519, "Patrick", 25.55, 545.00, "null"));

        //stopsModels.add(new StopsModel(32, "Hello", 32.2, 342.4, null));

//        StopsAdapter adapter = new StopsAdapter(stopsModels);
//       recyclerView.setAdapter(adapter);

        // GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        StopsAdapter adapter = new StopsAdapter(MainActivity.this, stopsModels);
        */
/*
        for(StopsModel sm: stopsModels) {
            resultsTextView.setText(sm.Name);
        }
*/
    }

    public void getStops() {
         new StopsAsyncTask(this, stopsModels).execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
    }
}