package com.example.peethr.wsbtest.models.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;

/**
 * Created by goracy on 14.03.18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Event[] event;

    public EventAdapter(Event[] event) {
        this.event = event;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bindEvent(event[position]);
    }

    @Override
    public int getItemCount() {
        return event.length;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView eventTitle;
        private TextView eventPlace;
        private ImageView eventImage;

        public EventViewHolder(View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventPlace = itemView.findViewById(R.id.eventPlace);
            eventImage = itemView.findViewById(R.id.eventImage);
        }

        private void bindEvent(Event event)
        {
            eventTitle.setText(event.getEventTitle());
            eventPlace.setText(event.getEventPlace());
            eventImage.setImageResource(event.getEventImage());
        }
    }
}
