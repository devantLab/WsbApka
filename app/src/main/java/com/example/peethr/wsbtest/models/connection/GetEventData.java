package com.example.peethr.wsbtest.models.connection;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by goracy on 14.03.18.
 */

public class GetEventData {

    private LinkedList<Event> event = new LinkedList();

    public LinkedList getDataFromInternet()
    {
        event.add(new Event("Koncert Żabsona", "Zapraszamy wszystkich zainteresowanych na koncert Żabsona!", R.drawable.zabson));
        event.add(new Event("Teator", "Tetra", R.drawable.ic_event_white_36dp));
        event.add(new Event("fds", "fdsfsdf", R.drawable.zabson));
        event.add(new Event("rewrwe", "fdsfsd", R.drawable.event_heart));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb));

        return event;
    }

}
