package com.example.peethr.wsbtest.presenters;


import android.graphics.Color;
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
import com.example.peethr.wsbtest.models.converters.GetImageId;
import com.example.peethr.wsbtest.models.data.rooms.RoomModel;
import com.example.peethr.wsbtest.models.data.rooms.Rooms;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;
import java.util.Map;

import static com.example.peethr.wsbtest.models.converters.DaysConverter.getDayName;
import static com.example.peethr.wsbtest.models.converters.RoomNamesNormalization.normalization;

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

    private ImageView showFloor;

    private SlidrInterface slider;

    // variables
    private String title;
    private String searchedRoom;

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
        spinner.setSelection(1);

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
                selectBuilding("showBuildingA");
                spinner.setSelection(1);
            }
        });

        btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBuilding("showBuildingB");


                spinner.setSelection(1);
            }
        });

        searchRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedRoom = normalization(searchRoomForm.getText().toString().toUpperCase());

                if (rooms.containsKey(searchedRoom)) {
                    showRoomInfo(rooms.get(searchedRoom));
                    selectRoom(searchedRoom);
                } else
                    Toast.makeText(MapActivity.this, "Nie znaleziono pomieszczenia", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showRoomInfo(RoomModel selectedRoom) {
        roomName.setText(selectedRoom.getBuilding() + selectedRoom.getRoomNumber());
        roomDescription.setText(selectedRoom.getRoomDescription());
        phoneNumbers.setText("Tel: " + selectedRoom.getPhoneNumbers().get(0).toString());

            setUI(monday, selectedRoom, "monday");
            setUI(tuesday, selectedRoom, "tuesday");
            setUI(wednesday, selectedRoom, "wednesday");
            setUI(thursday, selectedRoom, "thursday");
            setUI(friday, selectedRoom, "friday");
            setUI(saturday, selectedRoom, "saturday");
            setUI(sunday, selectedRoom, "sunday");

        spinner.setSelection(selectedRoom.getFloor()+1);

    }

    private void setUI(TextView textView, RoomModel room, String day)
    {
        boolean ifOpen = room.getOpeningHours().get(day + "Opening").equals("-1") ? false : true;
        String dayName = getDayName(day);

        if (ifOpen) {
            textView.setText(dayName + ": " +
                    room.getOpeningHours().get(day + "Opening") +
                    " - " +
                    room.getOpeningHours().get(day + "Closing"));
        } else {
            textView.setText(dayName + ": nieczynne");
        }

    }

    private void selectBuilding(String targetBuilding)
    {
        switch (targetBuilding) {
            case "showBuildingA" :
                showFloor.setImageResource(R.drawable.aparter);
                break;
            case "showBuildingB" :
                showFloor.setImageResource(R.drawable.bparter);
                break;
        }
    }

    private void selectRoom(String searchedRoom) {
        showFloor.setImageResource(GetImageId.getImageId(getApplicationContext(), "zz_" + searchedRoom.toLowerCase()));
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

        showFloor = findViewById(R.id.showFloor);

        searchRoomForm = findViewById(R.id.searchRoomForm);
    }
}
