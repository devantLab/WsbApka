package com.example.peethr.wsbtest.presenters;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.io.FileOutputStream;

public class MapActivity extends AppCompatActivity  {

    private ConstraintLayout floor0Container;

    private Toolbar toolbar;

    private Button btn_a;
    private Button btn_b;

    private SlidrInterface slider;

    private String title;
    private boolean floor0visibility = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        findViews();

        title = "Mapa uczelni";
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slider = Slidr.attach(this);

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor0visibility = !floor0visibility;

                int isVisible = floor0visibility ? View.VISIBLE : View.INVISIBLE;

                floor0Container.setVisibility(isVisible);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void findViews(){

        floor0Container = findViewById(R.id.floor0Container);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        toolbar = findViewById(R.id.toolbar);
    }
}
