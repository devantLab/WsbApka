package com.example.peethr.wsbtest.fragments.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: think how to connect to the database
public class EventContent {

    /**
     * List of all Events downloaded from the DB
     */
    public static final List<EventItem> ITEMS = new ArrayList<>();
    /**
     * ITEM_MAP allows access to events on the list using id
     */
    public static final Map<Long, EventItem> ITEM_MAP = new HashMap<>();

    /**
     * COUNT of events in the DB, it will be downloaded from the database
     *
     */
    private static final int COUNT = 1000;

    static {
        for (long i = 1; i <= COUNT; i++) {
            addItem(createEventItem(i));
        }
    }

    private static void addItem(EventItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static EventItem createEventItem(long position) {
        return new EventItem(position, "title", "city" ,"date", "time", "place", "url");
    }


}
