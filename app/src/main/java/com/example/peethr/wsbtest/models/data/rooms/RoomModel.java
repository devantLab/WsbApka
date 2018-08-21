package com.example.peethr.wsbtest.models.data.rooms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomModel {

    private int floor;
    private String building;
    private String roomNumber;
    private String roomDescription;
    private List<Integer> phoneNumbers;
    private Map<String, String> openingHours = new HashMap<>();

    public RoomModel(
            int floor,
            String building,
            String roomNumber,
            String roomDescription,
            List<Integer> phoneNumbers,
            String mondayOpening,
            String mondayClosing,
            String tuesdayOpening,
            String tuesdayClosing,
            String wednesdayOpening,
            String wednesdayClosing,
            String thursdayOpening,
            String thursdayClosing,
            String fridayOpening,
            String fridayClosing,
            String saturdayOpening,
            String saturdayClosing,
            String sundayOpening,
            String sundayClosing
    ) {
        this.floor = floor;
        this.building = building;
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.phoneNumbers = phoneNumbers;

        openingHours.put("mondayOpening", mondayOpening);
        openingHours.put("mondayClosing", mondayClosing);
        openingHours.put("tuesdayOpening", tuesdayOpening);
        openingHours.put("tuesdayClosing", tuesdayClosing);
        openingHours.put("wednesdayOpening", wednesdayOpening);
        openingHours.put("wednesdayClosing", wednesdayClosing);
        openingHours.put("thursdayOpening", thursdayOpening);
        openingHours.put("thursdayClosing", thursdayClosing);
        openingHours.put("fridayOpening", fridayOpening);
        openingHours.put("fridayClosing", fridayClosing);
        openingHours.put("saturdayOpening", saturdayOpening);
        openingHours.put("saturdayClosing", saturdayClosing);
        openingHours.put("sundayOpening", sundayOpening);
        openingHours.put("sundayClosing", sundayClosing);

    }

    public int getFloor() {
        return floor;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Map<String, String> getOpeningHours() {
        return openingHours;
    }
}
