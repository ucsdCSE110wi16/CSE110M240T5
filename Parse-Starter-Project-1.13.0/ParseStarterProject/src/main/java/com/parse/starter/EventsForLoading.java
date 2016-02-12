package com.parse.starter;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

/**
 * EventsForLoading is Event objects that are designed to load into the
 * recycler view (Events screen).
 * Created by jennywong on 2/8/16.
 */
public class EventsForLoading {

    private String eventName;
    private String location;
    private String date;
    private String details;

    // Create empty parameter ctor

    public EventsForLoading(String eventNameP, String locationP, String dateP, String detailsP) {
        this.eventName = eventNameP;
        this.location = locationP;
        this.date = dateP;
        this.details = detailsP;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDate() {
        return this.date;
    }

    public String getDetails() {
        return this.details;
    }

    public static ArrayList<EventsForLoading> createEventsList(int size) {
        ArrayList<EventsForLoading> events = new ArrayList<EventsForLoading>(size);

        EventsForLoading event1 = new EventsForLoading("Pop up restaurant", "CSE Basement", "TODAY!", "");
        EventsForLoading event2 = new EventsForLoading("Pity party, need a +1", "RIMAC", "Feb 11", "");
        EventsForLoading event3 = new EventsForLoading("Basketball game COME THRU", "My house", "2/11", "");

        events.add(event1);
        events.add(event2);
        events.add(event3);

        return events;
    }

//    /**
//     * TODO
//     * @param v
//     */
//    public void replyButtonOnClick(View v) {
//        EventsActivity.viewEventDetails(v);
//    }

}
