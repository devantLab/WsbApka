package com.example.peethr.wsbtest.presenters;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import com.example.peethr.wsbtest.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class MapActivity extends AppCompatActivity  {

    private Spinner spinner;

    private ImageView a019;
    private ImageView a017;

    private ConstraintLayout buildingAFloor0Container;

    private Toolbar toolbar;

    private Button btn_a;
    private Button btn_b;

    private SlidrInterface slider;

    private String title;
    private boolean floor0visibility = true;

    private String[] levels = {"Piętro -1", "Piętro 0", "Piętro 1", "Piętro 2", "Piętro 3", "Piętro 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        findViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item , levels);

        spinner.setAdapter(adapter);

        title = "Mapa uczelni";
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slider = Slidr.attach(this);

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a017.setColorFilter(Color.RED, PorterDuff.Mode.ADD);
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor0visibility = !floor0visibility;

                int isVisible = floor0visibility ? View.VISIBLE : View.INVISIBLE;

                buildingAFloor0Container.setVisibility(isVisible);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void findViews(){

        spinner = findViewById(R.id.spinner);

        a017 = findViewById(R.id.a017);
        a019 = findViewById(R.id.a019);

        buildingAFloor0Container = findViewById(R.id.buildingAFloor0Container);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        toolbar = findViewById(R.id.toolbar);
    }
}
