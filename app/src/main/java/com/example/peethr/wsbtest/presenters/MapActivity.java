package com.example.peethr.wsbtest.presenters;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;

import java.io.FileOutputStream;

public class MapActivity extends AppCompatActivity {

    private Button resetLanguageButton;

    private Toolbar toolbar;
    private String title;

    private Button btn_a;
    private Button btn_b;

    private ManageSharedPreferences manageSharedPreferences;

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



        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void findViews(){
        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        toolbar = findViewById(R.id.toolbar);
    }
}
