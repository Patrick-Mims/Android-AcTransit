package com.example.android_actransit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_actransit.R;
import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    Context context;
    ArrayList model;
    public StopsAdapter(Context context, ArrayList model) {
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
        holder.stopId.setText((String) model.get(position));
    }
    @Override
    public int getItemCount() {
        return model.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stopId;
        public ViewHolder(@NonNull View view) {
            super(view);
            stopId = view.findViewById(R.id.stop_id);
        }
    }
}