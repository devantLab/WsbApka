package com.example.peethr.wsbtest.models.connection;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;

/**
 * Created by goracy on 14.03.18.
 */

public class GetEventData {

    private Event[] event = new Event[5];

    public Event[] getDataFromInternet()
    {
        event[0] = new Event("Koncert Żabsona", "Uczelnia", R.drawable.zabson);
        event[1] = new Event("Teator", "Tetra", R.drawable.ic_event_white_36dp);
        event[2] = new Event("fds", "fdsfsdf", R.drawable.zabson);
        event[3] = new Event("rewrwe", "fdsfsd", R.drawable.event_heart);
        event[4] = new Event("Koncert", "Uczelnia", R.drawable.wsb);

        return event;
    }

}
