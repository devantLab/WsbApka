package pl.devant.wsbnotify.presenters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.LinkedList;

import pl.devant.wsbnotify.R;
import pl.devant.wsbnotify.models.adapters.RecentAppAdapter;
import pl.devant.wsbnotify.models.connection.GetRecentAppData;
import pl.devant.wsbnotify.models.data.recentApps.RecentApp;


public class RecentAppActivity extends AppCompatActivity {

    private TextView emptyRecyclerTextView;

    private LinkedList<RecentApp> recentApps = new LinkedList();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout recentAppSwipeRefresh;

    private Toolbar toolbar;

    private SlidrInterface slidr;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_app);

        String title = "Polecane Aplikacje";
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);

        recyclerView = findViewById(R.id.recentAppRecyclerView);
        recentAppSwipeRefresh = findViewById(R.id.recentAppSwipeRefresh);
        emptyRecyclerTextView = findViewById(R.id.recentAppEmptyRecyclerTextView);

        slidr = Slidr.attach(this);

        getRecentAppData();
        refreshPlace();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void getRecentAppData(){
        GetRecentAppData getRecentAppData = new GetRecentAppData();
        recentApps = getRecentAppData.getDataFromInternet();

        RecentAppAdapter adapter = new RecentAppAdapter(recentApps);
        if (adapter.getItemCount() == 0)
        {
            emptyRecyclerTextView.setVisibility(View.VISIBLE);

        } else {

            emptyRecyclerTextView.setVisibility(View.GONE);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecentAppActivity.this);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
    private void refreshPlace(){
        recentAppSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.CYAN);
        recentAppSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getRecentAppData();
                Toast.makeText(RecentAppActivity.this, "Odswieżono aplikacje", Toast.LENGTH_SHORT).show();
                recentAppSwipeRefresh.setRefreshing(false);
            }
        });
    }


}
