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


        toolbar = findViewById(R.id.toolbar);

        searchRoomForm = findViewById(R.id.searchRoomForm);
    }
}
