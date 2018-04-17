package com.example.peethr.wsbtest.presenters;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.preferences.ManageSharedPreferences;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class HoursActivity extends AppCompatActivity {

    private Button resetLanguageButton;

    private Toolbar toolbar;
    private String title;

    private ManageSharedPreferences manageSharedPreferences;

    private SlidrInterface slider;

    private TextView tv;

    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference conditionRef = rootRef.child("events").child("0").child("event_description");


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

        slider = Slidr.attach(this);

        manageSharedPreferences = new ManageSharedPreferences(this);

        resetLanguageButton = findViewById(R.id.resetLanguageButton);

        resetLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageSharedPreferences.setLanguage("default");
            }
        });


        tv = findViewById(R.id.tv);

    }


    @Override
    protected void onStart() {
        super.onStart();
        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                tv.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
