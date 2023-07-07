package com.example.android_actransit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_actransit.Helpers.StopsAsyncTask;
import com.example.android_actransit.Models.StopsModel;
import com.example.android_actransit.R;
import java.util.ArrayList;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    // The type of data that's being passed in.
    // ArrayList<StopsModel> stopsModelAdapter;
    ArrayList stopsModel;
    Context context;

    // Constructor
    //public StopsAdapter(Context context, ArrayList<StopsModel> stopsModel) {
    public StopsAdapter(Context context, ArrayList stopsModel) {
        this.context = context;
        this.stopsModel = stopsModel;
    }

    // The recycler view calls the method repeatedly when the recycler view is first constructed to
    // build the set of view holders that will be displayed on the screen.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_layout, parent, false);
        return new ViewHolder(cv);
    }

    // The recycler view calls this method when it wants to use (or reuse) a view holder for a new piece of data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // StopsAsyncTask sat = new StopsAsyncTask(holder.cardView.getContext(), stopsModel);
        // sat.execute();.execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
        // holder.text.setText(stopsModel.get(position).toString());
        // holder.text.setText((StopsModel) StopsModel.get(position));
     //   holder.cardView.setText(0);
//       CardView cv = holder.cardView;

//        TextView tvStopId = cv.findViewById(R.id.stop_id);
//        tvStopId.setText("Patrick");
        /*
        TextView tvName =  cardView.findViewById(R.id.name);
        TextView tvLatitude = cardView.findViewById(R.id.latitude);
        TextView tvLongitude = cardView.findViewById(R.id.longitude);
        TextView tvScheduledTime = cardView.findViewById(R.id.scheduled_time);
         */
    }

    // The length of the captions array equals the number of data items in the recycler view.
    @Override
    public int getItemCount() {
        return stopsModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        TextView text;
        public ViewHolder(@NonNull CardView v) {
            super(v);
            text = v.findViewById(R.id.textView);
            cardView = v;
        }
    }
}