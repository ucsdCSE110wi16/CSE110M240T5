package com.parse.starter;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class interfaces with the database to Bundle events into containers.
 * Created by Johnathan, Jenny
 */
public class EventsBundler {

    /**
     * Fetches numEvents events from the database, largest date value first.
     *
     * @param numEvents - The number of events to get
     * @return ArrayList of numEvents most recent events.
     */
    public static List<Event> recentEvents(int numEvents) throws ParseException {
        List<Event> events = new ArrayList<Event>(numEvents);
        // Get numEvents events from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        query.addDescendingOrder("date"); // sort by date most recent first
        query.setLimit(numEvents);
        List<ParseObject> userEvents = query.find();
        for (ParseObject ev : userEvents) {
            String title = ev.getString("title");
            String loc = ev.getString("loc");
            Date date = ev.getDate("date");
            String desc = ev.getString("description");
            String id = ev.getObjectId();
            events.add(new Event(title, loc, date, desc, id));
        }
        return events;
    }

    public static List<Event> testEvents(int numEvents) {
        List<Event> events = new ArrayList<Event>(numEvents);

        events.add(new Event("title1", "loc1", new Date(), "desc1", "111"));
        events.add(new Event("title2", "loc2,", new Date(), "desc2", "222"));
        events.add(new Event("title3", "loc3,", new Date(), "desc3", "333"));
        return events;
    }

    /**
     * getEvent Returns an Event object created from the entry in the db
     *          corresponding to the input id
     *
     * @param id - identifier of Event object
     * @return Event object
     */
    public static Event getEvent(final String id) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        ParseObject parEvent = query.get(id);

        Log.d("event", "Retrieved event id: " + id);
        String title = parEvent.getString("title");
        String loc = parEvent.getString("loc");
        Date date = parEvent.getDate("date");
        String desc = parEvent.getString("description");
        return new Event(title, loc, date, desc, id);
    }

}