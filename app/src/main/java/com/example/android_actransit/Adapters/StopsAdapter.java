package com.example.android_actransit.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;

import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    private final ArrayList<StopsModel> model;

    public StopsAdapter(ArrayList<StopsModel> model) {
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StopsModel mod = model.get(position);

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
        public final TextView stopId;

        public final TextView name;
        public final TextView latitude;
        public final TextView longitude;
        public final TextView scheduledTime;
        public final RecyclerView recyclerView;
        public ViewHolder(@NonNull View view) {
            super(view);

            recyclerView = view.findViewById(R.id.recyclerView);
            stopId = view.findViewById(R.id.stop_id);
            name = view.findViewById(R.id.name);
            latitude = view.findViewById(R.id.latitude);
            longitude = view.findViewById(R.id.longitude);
            scheduledTime = view.findViewById(R.id.scheduled_time);
        }
    }
}