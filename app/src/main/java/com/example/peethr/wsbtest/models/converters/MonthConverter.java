package com.example.peethr.wsbtest.models.converters;

import android.util.Log;

/**
 * Created by goracy on 21.04.18.
 */

public class MonthConverter {
    public static String nameOfMonth(int eventMonth, String language) {
        String monthName = "reload";
        Log.wtf("wtf",language);
        if (language.equals("en")) {
            switch (eventMonth) {
                case 0:
                    monthName = "Jan";
                    break;
                case 1:
                    monthName = "Feb";
                    break;
                case 2:
                    monthName = "Mar";
                    break;
                case 3:
                    monthName = "Apr";
                    break;
                case 4:
                    monthName = "May";
                    break;
                case 5:
                    monthName = "Jun";
                    break;
                case 6:
                    monthName = "Jul";
                    break;
                case 7:
                    monthName = "Aug";
                    break;
                case 8:
                    monthName = "Sep";
                    break;
                case 9:
                    monthName = "Oct";
                    break;
                case 10:
                    monthName = "Nov";
                    break;
                case 11:
                    monthName = "Dec";
                    break;
            }
        }
        else if(language.equals("pl"))
        {
            switch (eventMonth) {
                case 0:
                    monthName = "Sty";
                    break;
                case 1:
                    monthName = "Lut";
                    break;
                case 2:
                    monthName = "Mar";
                    break;
                case 3:
                    monthName = "Kwi";
                    break;
                case 4:
                    monthName = "Maj";
                    break;
                case 5:
                    monthName = "Cze";
                    break;
                case 6:
                    monthName = "Lip";
                    break;
                case 7:
                    monthName = "Sie";
                    break;
                case 8:
                    monthName = "Wrz";
                    break;
                case 9:
                    monthName = "Paź";
                    break;
                case 10:
                    monthName = "Lis";
                    break;
                case 11:
                    monthName = "Gru";
                    break;
            }
        }
        else if (language.equals("ru"))
        {
            switch (eventMonth) {
                case 0:
                    monthName = "янв";
                    break;
                case 1:
                    monthName = "февр";
                    break;
                case 2:
                    monthName = "март";
                    break;
                case 3:
                    monthName = "апр";
                    break;
                case 4:
                    monthName = "май";
                    break;
                case 5:
                    monthName = "июнь";
                    break;
                case 6:
                    monthName = "июль";
                    break;
                case 7:
                    monthName = "авг";
                    break;
                case 8:
                    monthName = "сент";
                    break;
                case 9:
                    monthName = "окт";
                    break;
                case 10:
                    monthName = "ноябрь";
                    break;
                case 11:
                    monthName = "дек";
                    break;
            }
        }
        Log.i("name", monthName);
        return monthName;

    }

}
