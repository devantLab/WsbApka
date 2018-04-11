package com.example.peethr.wsbtest.models.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.places.Place;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;


/**
 * Created by goracy on 11.04.18.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    private LinkedList<Place> place = new LinkedList();
    private Context context;

    public PlaceAdapter(LinkedList<Place> place) {
        this.place = place;
    }


    @NonNull
    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_activity_item,
                parent, false);
        PlaceViewHolder viewHolder = new PlaceViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        holder.bindPlace(place.get(position));
    }


    @Override
    public int getItemCount() {
        return place.size();
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView placeTitle;
        private TextView placeDescription;
        private ImageView placeImage;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            placeTitle = itemView.findViewById(R.id.placeTitle);
            placeDescription = itemView.findViewById(R.id.placeDescription);
            placeImage = itemView.findViewById(R.id.placeImage);
            itemView.setOnClickListener(this);
        }

        private void bindPlace(Place place)
        {
            placeTitle.setText(place.getPlaceTitle());
            placeDescription.setText(place.getPlaceDescription());
            Picasso.get()
                    .load(place.getPlaceImage())
                    .into(placeImage);
        }

        @Override
        public void onClick(View v) {

        }
    }
}