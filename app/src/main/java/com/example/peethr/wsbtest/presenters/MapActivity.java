package com.example.peethr.wsbtest.presenters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;

public class MapActivity extends AppCompatActivity {

    private Button resetLanguageButton;

    private ManageSharedPreferences manageSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        manageSharedPreferences = new ManageSharedPreferences(this);

        resetLanguageButton = findViewById(R.id.resetLanguageButton);

        resetLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageSharedPreferences.setLanguage("default");
            }
        });
    }
}
