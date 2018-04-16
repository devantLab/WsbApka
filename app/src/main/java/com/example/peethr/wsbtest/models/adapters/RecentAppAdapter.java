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
import com.example.peethr.wsbtest.models.data.recentApps.RecentApp;
import com.example.peethr.wsbtest.presenters.RecentAppDescription;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by goracy on 11.04.18.
 */

public class RecentAppAdapter extends RecyclerView.Adapter<RecentAppAdapter.RecentAppViewHolder> {

    private LinkedList<RecentApp> recentApp = new LinkedList();
    private Context context;

    public RecentAppAdapter(LinkedList<RecentApp> recentApp) {
        this.recentApp = recentApp;
    }


    @NonNull
    @Override
    public RecentAppAdapter.RecentAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_app_activity_item,
                parent, false);
        RecentAppViewHolder viewHolder = new RecentAppViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAppViewHolder holder, int position) {
        holder.bindRecentApp(recentApp.get(position));
    }


    @Override
    public int getItemCount() {
        return recentApp.size();
    }


    public class RecentAppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView recentAppTitle;
        private TextView recentAppDescription;
        private ImageView recentAppImage;

        public RecentAppViewHolder(View itemView) {
            super(itemView);

            recentAppTitle = itemView.findViewById(R.id.recentAppTitle);
            recentAppDescription = itemView.findViewById(R.id.recentAppDescription);
            recentAppImage = itemView.findViewById(R.id.recentAppImage);
            itemView.setOnClickListener(this);
        }

        private void bindRecentApp(RecentApp recentApp)
        {
            recentAppTitle.setText(recentApp.getRecentAppTitle());
            recentAppDescription.setText(recentApp.getRecentAppDescription());
            Picasso.get()
                    .load(recentApp.getRecentAppImage())
                    .into(recentAppImage);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) { startRecentAppDescription(); }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void startRecentAppDescription() {
            Intent intent = new Intent(context, RecentAppDescription.class);
            intent.putExtra("clickedRecentApp", recentApp.get(this.getAdapterPosition()));

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, recentAppImage,
                    "recentAppSharedImage");
            context.startActivity(intent, options.toBundle());
        }
    }
}
