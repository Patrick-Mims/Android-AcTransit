package com.example.android_actransit.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.MainActivity;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;
import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    private CardView cardView;
    private final ArrayList<StopsModel> model;

    public StopsAdapter(Context context, ArrayList<StopsModel> model) {
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_layout, parent, false);
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        // CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
        StopsAsyncTask stopsAsyncTask = new StopsAsyncTask(context, model);
        stopsAsyncTask.execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
         */

        StopsModel mod = model.get(position);

        holder.stopId.setText(String.format("%s", mod.getStopId()));
        holder.name.setText(String.format("%s", mod.getName()));
        holder.latitude.setText(String.format("%s", mod.getLatitude()));
        holder.longitude.setText(String.format("%s", mod.getLatitude()));
        holder.scheduledTime.setText(String.format("%s", mod.getScheduledTime()));

//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
 //       holder.recyclerView.setAdapter(new StopsAdapter(context, model));
    }
    @Override
    public int getItemCount() {
        // return model.size();
        /*
        if(model != null) {
            return model.size();
        } else {
            return 0;
        }
        */
        return model.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView stopId;
        public final TextView name;
        public final TextView latitude;
        public final TextView longitude;
        public final TextView scheduledTime;
        public final RecyclerView recyclerView;
        public ViewHolder(@NonNull View view) {
        // public ViewHolder(CardView view) {
            super(view);
        //    cardView = view;
            recyclerView = view.findViewById(R.id.recyclerView);
            stopId = view.findViewById(R.id.stop_id);
            name = view.findViewById(R.id.name);
            latitude = view.findViewById(R.id.latitude);
            longitude = view.findViewById(R.id.longitude);
            scheduledTime = view.findViewById(R.id.scheduled_time);
        }
    }
}