package com.example.android_actransit.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;
import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    // The type of data that's being passed in.
    private ArrayList<StopsModel> stopsModel = new ArrayList<>();

    // Constructor
    public StopsAdapter(ArrayList<StopsModel> stopsModel) {
        this.stopsModel = stopsModel;
    }

    // The recycler view calls the method repeatedly when the recycler view is first constructed to
    // build the set of view holders that will be displayed on the screen.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_layout, parent, false);
        TextView tvStopId = (TextView) cardView.findViewById(R.id.stop_id);
        TextView tvName = (TextView) cardView.findViewById(R.id.name);
        TextView tvLatitude = (TextView) cardView.findViewById(R.id.latitude);
        TextView tvLongitude = (TextView) cardView.findViewById(R.id.longitude);
        TextView tvScheduledTime = (TextView) cardView.findViewById(R.id.scheduled_time);

        return new ViewHolder(cardView);
    }

    // The recycler view calls this method when it wants to use (or reuse) a view holder for a new piece of data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
    }

    // The length of the captions array equals the number of data items in the recycler view.
    @Override
    public int getItemCount() {
        return stopsModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView v) {
            super(v);
            cardView = v;
        }
    }
}