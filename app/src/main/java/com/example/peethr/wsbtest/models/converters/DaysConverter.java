package com.example.peethr.wsbtest.models.converters;

public class DaysConverter {

    public static String getDayName(String day) {
        switch (day){
            case "monday" :
                return "Poniedziałek";

            case "tuesday" :
                return "Wtorek";

            case "wednesday" :
                return "Środa";

            case "thursday" :
                return "Czwartek";

            case "friday" :
                return "Piątek";

            case "saturday" :
                return "Sobota";

            case "sunday" :
                return "Niedziela";

        }
        return day;
    }

}
