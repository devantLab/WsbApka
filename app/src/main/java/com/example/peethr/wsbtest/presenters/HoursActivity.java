package com.example.peethr.wsbtest.presenters;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;


public class HoursActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button resetLanguageButton;

    private Toolbar toolbar;
    private String title;

    private ManageSharedPreferences manageSharedPreferences;

    private SlidrInterface slider;

    private Spinner hoursSpinner;
    private String[] departmentsName = {"Dziekanat","Dział zagraniczny","Biuro karier"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        title = "Godziny otwarcia działów";
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hoursSpinner = findViewById(R.id.hoursSpinner);
        hoursSpinner.setOnItemSelectedListener(this);

        slider = Slidr.attach(this);

        manageSharedPreferences = new ManageSharedPreferences(this);

        resetLanguageButton = findViewById(R.id.resetLanguageButton);

        resetLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageSharedPreferences.setLanguage("default");
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentsName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hoursSpinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


}
