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

public class MapActivity extends AppCompatActivity {

    private Button resetLanguageButton;

    private Toolbar toolbar;
    private String title;

    private ManageSharedPreferences manageSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        title = "Mapa uczelni";
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        manageSharedPreferences = new ManageSharedPreferences(this);

        resetLanguageButton = findViewById(R.id.resetLanguageButton);

        resetLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageSharedPreferences.setLanguage("default");
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
