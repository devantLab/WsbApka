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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.rooms.RoomModel;
import com.example.peethr.wsbtest.models.data.rooms.Rooms;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MapActivity extends AppCompatActivity  {

    // ui
    private Spinner spinner;

    private ConstraintLayout buildingAFloor0Container;

    private Toolbar toolbar;

    private Button btn_a;
    private Button btn_b;
    private Button searchRoomButton;

    private EditText searchRoomForm;

    private TextView roomName;
    private TextView roomDescription;
    private TextView phoneNumbers;
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;


    private SlidrInterface slider;

    // variables
    private String title;
    private String searchedRoom;

    private boolean floor0visibility = true;

    private String[] levels = {"Piętro -1", "Piętro 0", "Piętro 1", "Piętro 2", "Piętro 3", "Piętro 4"};

    private Rooms allRooms = new Rooms();

    private Map<String, RoomModel> rooms = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        findViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item , levels);

        spinner.setAdapter(adapter);

        allRooms.createRooms();
        rooms = allRooms.getRooms();

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

        searchRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedRoom = searchRoomForm.getText().toString().toUpperCase();

                searchedRoom = normalization(searchedRoom);

                if (rooms.containsKey(searchedRoom)) {
                    showRoomInfo(rooms.get(searchedRoom));
                } else
                    Toast.makeText(MapActivity.this, "Nie znaleziono pomieszczenia", Toast.LENGTH_LONG).show();
            }
        });

    }

    private String normalization(String searchedRoom) {

        switch (searchedRoom)
        {
            case "A17" :
                return "A017";
            case "A19" :
                return "A019";
            default: return searchedRoom;
        }
    }

    private void showRoomInfo(RoomModel selectedRoom) {
        roomName.setText(selectedRoom.getBuilding() + selectedRoom.getRoomNumber());
        roomDescription.setText(selectedRoom.getRoomDescription());
        phoneNumbers.setText("Tel: " + selectedRoom.getPhoneNumbers().get(0).toString());

        boolean poniedzialek = selectedRoom.getOpeningHours().get("mondayOpening").equals("-1") ? false : true;

        if(poniedzialek) {
            monday.setText("Poniedziałek: " +
                    selectedRoom.getOpeningHours().get("mondayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("mondayClosing"));
        } else monday.setText("Poniedziałek: nieczynne");

        boolean wtorek = selectedRoom.getOpeningHours().get("tuesdayOpening").equals("-1") ? false : true;

        if(wtorek) {
            tuesday.setText("Wtorek: " +
                    selectedRoom.getOpeningHours().get("tuesdayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("tuesdayClosing"));
        } else tuesday.setText("Wtorek: nieczynne");

        boolean sroda = selectedRoom.getOpeningHours().get("wednesdayOpening").equals("-1") ? false : true;

        if(sroda) {
            wednesday.setText("Środa: " +
                    selectedRoom.getOpeningHours().get("wednesdayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("wednesdayClosing"));
        } else wednesday.setText("Środa: nieczynne");

        boolean czwartek = selectedRoom.getOpeningHours().get("thursdayOpening").equals("-1") ? false : true;

        if(czwartek) {
            thursday.setText("Czwartek: " +
                    selectedRoom.getOpeningHours().get("thursday   Opening") + " - " +
                    selectedRoom.getOpeningHours().get("thursday   Closing"));
        } else thursday.setText("Czwartek: nieczynne");

        boolean piatek = selectedRoom.getOpeningHours().get("fridayOpening").equals("-1") ? false : true;

        if(piatek) {
            friday.setText("Piątek: " +
                    selectedRoom.getOpeningHours().get("fridayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("fridayClosing"));
        } else friday.setText("Piątek: nieczynne");

        boolean sobota = selectedRoom.getOpeningHours().get("saturdayOpening").equals("-1") ? false : true;

        if(sobota) {
            saturday.setText("Sobota: " +
                    selectedRoom.getOpeningHours().get("saturdayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("saturdayClosing"));
        } else saturday.setText("Sobota: nieczynne");

        boolean niedziela = selectedRoom.getOpeningHours().get("sundayOpening").equals("-1") ? false : true;

        if(niedziela) {
            sunday.setText("Niedziela: " +
                    selectedRoom.getOpeningHours().get("sundayOpening") + " - " +
                    selectedRoom.getOpeningHours().get("sundayClosing"));
        } else sunday.setText("Niedziela: nieczynne");


        spinner.setSelection(selectedRoom.getFloor()+1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void findViews(){

        spinner = findViewById(R.id.spinner);

        buildingAFloor0Container = findViewById(R.id.buildingAFloor0Container);

        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        searchRoomButton = findViewById(R.id.searchRoomButton);

        roomName = findViewById(R.id.roomName);
        roomDescription = findViewById(R.id.roomDescription);
        phoneNumbers = findViewById(R.id.phoneNumbers);
        monday = findViewById(R.id.mondayHours);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        sunday = findViewById(R.id.sunday);

        toolbar = findViewById(R.id.toolbar);

        searchRoomForm = findViewById(R.id.searchRoomForm);
    }
}
