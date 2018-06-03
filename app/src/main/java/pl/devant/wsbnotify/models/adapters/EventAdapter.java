package pl.devant.wsbnotify.models.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.models.converters.MonthConverter;
import pl.devant.wsbnotify.models.data.events.Event;
import pl.devant.wsbnotify.models.data.preferences.ManageSharedPreferences;
import pl.devant.wsbnotify.presenters.EventDescription;

/**
 * Created by goracy on 14.03.18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private LinkedList<Event> event = new LinkedList();
    private Context context;
    private ManageSharedPreferences manageSharedPreferences;
    private String language;

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
        manageSharedPreferences = new ManageSharedPreferences(context);
        language = manageSharedPreferences.getLanguage();
        Log.i("LANG", manageSharedPreferences.getLanguage()+"");
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
        private TextView eventCity;
        private TextView eventStreet;
        private TextView eventDay;
        private TextView eventMonth;
        private TextView eventTimeStart;
        private TextView eventTimeEnd;
        private ImageView eventImage;


        public EventViewHolder(View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventCity = itemView.findViewById(R.id.eventCity);
            eventStreet = itemView.findViewById(R.id.eventStreet);
            eventDay = itemView.findViewById(R.id.eventDay);
            eventMonth = itemView.findViewById(R.id.eventMonth);
            eventTimeStart = itemView.findViewById(R.id.eventTimeStart);
            eventTimeEnd = itemView.findViewById(R.id.eventTimeEnd);
            eventImage = itemView.findViewById(R.id.eventImage);
            itemView.setOnClickListener(this);
        }

        private void bindEvent(Event event)
        {
            eventTitle.setText(event.getEventTitle());
            eventCity.setText(event.getEventCity()+",");
            eventStreet.setText(event.getEventStreet());
            eventDay.setText(event.getEventDay()+"");
            eventMonth.setText(MonthConverter.nameOfMonth(event.getEventMonth(), manageSharedPreferences.getLanguage()));
            if(event.getEventTimeEnd()!="0"){
                eventTimeStart.setText(event.getEventTimeStart()+"-");
                eventTimeEnd.setText(event.getEventTimeEnd());
            }
            else {
                eventTimeStart.setText(event.getEventTimeStart());
            }
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
