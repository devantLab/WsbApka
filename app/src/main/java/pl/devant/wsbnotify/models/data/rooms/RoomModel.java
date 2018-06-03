package pl.devant.wsbnotify.models.data.rooms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomModel {

    private int floor;
    private String building;
    private int roomNumber;
    private String roomDescription;
    private List<Integer> phoneNumbers;
    private Map<String, Integer> openingHours = new HashMap<>();

    public RoomModel(
            int floor,
            String building,
            int roomNumber,
            String roomDescription,
            List<Integer> phoneNumbers,
            int mondayOpening,
            int mondayClosing,
            int tuesdayOpening,
            int tuesdayClosing,
            int wednesdayOpening,
            int wednesdayClosing,
            int thursdayOpening,
            int thursdayClosing,
            int fridayOpening,
            int fridayClosing,
            int saturdayOpening,
            int saturdayClosing,
            int sundayOpening,
            int sundayClosing
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

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Map<String, Integer> getOpeningHours() {
        return openingHours;
    }
}
