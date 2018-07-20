package com.example.peethr.wsbtest.models.converters;

public class RoomNamesNormalization {
    public static String normalization(String searchedRoom) {

        switch (searchedRoom)
        {
            case "A17" :
                return "A017";
            case "A19" :
                return "A019";
            default: return searchedRoom;
        }
    }
}
