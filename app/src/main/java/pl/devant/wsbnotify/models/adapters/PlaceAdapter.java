package pl.devant.wsbnotify.models.adapters;

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

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.models.data.places.Place;
import pl.devant.wsbnotify.presenters.PlaceDescription;

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
        private TextView placeCity;
        private TextView placeStreet;
        private TextView placeCategory;
        private ImageView placeImage;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            placeTitle = itemView.findViewById(R.id.placeTitle);
            placeCity = itemView.findViewById(R.id.placeCity);
            placeImage = itemView.findViewById(R.id.placeImage);
            placeStreet = itemView.findViewById(R.id.placeStreet);
            placeCategory = itemView.findViewById(R.id.placeCategory);
            itemView.setOnClickListener(this);
        }

        private void bindPlace(Place place)
        {
            placeTitle.setText(place.getPlaceTitle());
            placeCity.setText(place.getPlaceCity()+",");
            placeStreet.setText(place.getPlaceStreet());
            placeCategory.setText(place.getPlaceCategory());
            Picasso.get()
                    .load(place.getPlaceImage())
                    .into(placeImage);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) { startPlaceDescription(); }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void startPlaceDescription() {
            Intent intent = new Intent(context, PlaceDescription.class);
            intent.putExtra("clickedPlace", place.get(this.getAdapterPosition()));

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, placeImage,
                    "placeSharedImage");
            context.startActivity(intent, options.toBundle());
        }
    }
}
