package com.example.peethr.wsbtest.models.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.CareerEvent;
import com.example.peethr.wsbtest.presenters.CareerEventDescription;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by goracy on 11.04.18.
 */

public class CareerEventAdapter extends RecyclerView.Adapter<CareerEventAdapter.CareerEventViewHolder> {
    private LinkedList<CareerEvent> careerEvent = new LinkedList();
    private Context context;

    public CareerEventAdapter(LinkedList<CareerEvent> careerEvent) {
        this.careerEvent = careerEvent;
    }

    @NonNull
    @Override
    public CareerEventAdapter.CareerEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_career_item, parent, false);
        CareerEventAdapter.CareerEventViewHolder viewHolder = new CareerEventAdapter.CareerEventViewHolder(view);

        context = parent.getContext();

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CareerEventAdapter.CareerEventViewHolder holder, int position) {
        holder.bindCareerEvent(careerEvent.get(position));
    }

    @Override
    public int getItemCount() {
        return careerEvent.size();
    }

    public class CareerEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView careerEventTitle;
        private TextView careerEventPlace;
        private ImageView careerEventImage;

        public CareerEventViewHolder(View itemView) {
            super(itemView);

            careerEventTitle = itemView.findViewById(R.id.careerEventTitle);
            careerEventPlace = itemView.findViewById(R.id.careerEventPlace);
            careerEventImage = itemView.findViewById(R.id.careerEventImage);
            itemView.setOnClickListener(this);
        }

        private void bindCareerEvent(CareerEvent careerEvent)
        {
            careerEventTitle.setText(careerEvent.getCareerEventTitle());
            careerEventPlace.setText(careerEvent.getCareerEventPlace());
            Picasso.get()
                    .load(careerEvent.getCareerEventImage())
                    .into(careerEventImage);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            startCareerEventDescription();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void startCareerEventDescription() {
            Intent intent = new Intent(context, CareerEventDescription.class);
            intent.putExtra("clickedCareerEvent", careerEvent.get(this.getAdapterPosition()));

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, careerEventImage,
                    "careerEventSharedImage");

            context.startActivity(intent, options.toBundle());
        }
    }
}
