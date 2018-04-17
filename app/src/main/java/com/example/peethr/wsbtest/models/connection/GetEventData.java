package com.example.peethr.wsbtest.models.connection;

import com.example.peethr.wsbtest.R;
import com.example.peethr.wsbtest.models.data.events.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                "https://www.planwallpaper.com/static/images/canberra_hero_image_JiMVvYU.jpg", lorem));
        event.add(new Event("Teator", "Tetra", "https://www.planwallpaper.com/static/images/9-credit-1.jpg", lorem));
        event.add(new Event("fds", "fdsfsdf", "https://www.planwallpaper.com/static/images/6F0CE738-6419-4CF4-8E8878246C2D2569.jpg", lorem));
        event.add(new Event("rewrwe", "fdsfsd", "https://www.planwallpaper.com/static/images/offset_WaterHouseMarineImages_62652-2-660x440.jpg", lorem));
        event.add(new Event("Koncert", "Uczelnia", "https://www.planwallpaper.com/static/images/6775415-beautiful-images.jpg", lorem));
        event.add(new Event("Koncert", "Uczelnia", "https://www.planwallpaper.com/static/images/Child-Girl-with-Sunflowers-Images.jpg", lorem));
        event.add(new Event("Koncert", "Uczelnia", "https://www.planwallpaper.com/static/images/Jennifer-in-Paradi_3204219n.jpg", lorem));

        return event;
    }

}
