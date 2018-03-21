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

    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam molestie sapien eleifend libero aliquet molestie. Morbi et mi malesuada, posuere lectus in, pulvinar nisi. Donec a aliquet est. Morbi quis eros sed diam aliquam dictum ac non elit. Donec at nisi sed purus varius rutrum nec eget enim. Pellentesque ac ligula non lacus iaculis suscipit. ";
    private LinkedList<Event> event = new LinkedList();

    public LinkedList getDataFromInternet()
    {


        event.add(new Event("Koncert Żabsona", "Zapraszamy wszystkich zainteresowanych na koncert Żabsona!",
                R.drawable.zabson, lorem));
        event.add(new Event("Teator", "Tetra", R.drawable.ic_event_white_36dp, lorem));
        event.add(new Event("fds", "fdsfsdf", R.drawable.zabson, lorem));
        event.add(new Event("rewrwe", "fdsfsd", R.drawable.event_heart, lorem));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb, lorem));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb, lorem));
        event.add(new Event("Koncert", "Uczelnia", R.drawable.wsb, lorem));

        return event;
    }

}
