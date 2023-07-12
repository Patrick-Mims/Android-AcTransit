package com.example.android_actransit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;
import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    Context context;
    ArrayList<StopsModel> model;
    public StopsAdapter(Context context, ArrayList<StopsModel> model) {
        this.context = context;
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StopsModel mod = model.get(position);

        StopsAsyncTask stopsAsyncTask = new StopsAsyncTask(context, model);
        stopsAsyncTask.execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");

        holder.stopId.setText(String.format("%s", mod.getStopId()));
        holder.stopId.setText(String.format("%s", mod.getStopId()));
        holder.name.setText(String.format("%s", mod.getName()));
        holder.latitude.setText(String.format("%s", mod.getLatitude()));
        holder.longitude.setText(String.format("%s", mod.getLatitude()));
        holder.scheduledTime.setText(String.format("%s", mod.getScheduledTime()));
    }
    @Override
    public int getItemCount() {
        return model.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stopId, name, latitude, longitude, scheduledTime;
        public ViewHolder(@NonNull View view) {
            super(view);
            stopId = view.findViewById(R.id.stop_id);
            name = view.findViewById(R.id.name);
            latitude = view.findViewById(R.id.latitude);
            longitude = view.findViewById(R.id.longitude);
            scheduledTime = view.findViewById(R.id.scheduled_time);
        }
    }
}