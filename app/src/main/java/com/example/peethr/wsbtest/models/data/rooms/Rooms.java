package com.example.peethr.wsbtest.models.data.rooms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rooms {

    private Map<String, RoomModel> rooms = new HashMap<>();

    public void createRooms() {

        // A001
        rooms.put("A017", new RoomModel(
                        0,
                        "A",
                        "017",
                        "Dziekanat",
                        Arrays.asList(
                                533065013, 100200300
                        ),
                        8,
                        16,
                        8,
                        16,
                        10,
                        18,
                        -1,
                        -1,
                        12,
                        15,
                        14,
                        18,
                        -1,
                        -1
                )
        );

        // A002
        rooms.put("A019", new RoomModel(
                        0,
                        "A",
                        "019",
                        "Dział zagraniczny",
                        Arrays.asList(
                                4444444, 2424242
                        ),
                        11,
                        16,
                        8,
                        16,
                        -1,
                        -1,
                        -1,
                        -1,
                        8,
                        15,
                        14,
                        18,
                        -1,
                        -1
                )
        );
    }

    public Map<String, RoomModel> getRooms() {
        return rooms;
    }
}
