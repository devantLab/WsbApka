package com.example.peethr.wsbtest.models.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.fragments.EventFragment;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.example.peethr.wsbtest.presenters.EventDescription;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by goracy on 14.03.18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private LinkedList<Event> event = new LinkedList();
    private Context context;


    public EventAdapter(LinkedList<Event> event) {
        this.event = event;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);

        context = parent.getContext();

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bindEvent(event.get(position));
    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView eventTitle;
        private TextView eventPlace;
        private ImageView eventImage;

        public EventViewHolder(View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventPlace = itemView.findViewById(R.id.eventPlace);
            eventImage = itemView.findViewById(R.id.eventImage);
            itemView.setOnClickListener(this);
        }

        private void bindEvent(Event event)
        {
            eventTitle.setText(event.getEventTitle());
            eventPlace.setText(event.getEventPlace());
            Picasso.get()
                    .load(event.getEventImage())
                    .into(eventImage);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            startEventDescription();
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void startEventDescription() {
            Intent intent = new Intent(context, EventDescription.class);
            intent.putExtra("clickedEvent", event.get(this.getAdapterPosition()));

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, eventImage,
                    "eventSharedImage");

            context.startActivity(intent, options.toBundle());
        }
    }
}
