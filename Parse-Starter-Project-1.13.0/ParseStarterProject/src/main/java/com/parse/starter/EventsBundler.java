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
     * Fetches numEvents events from the database, largest date value first.
     * Fetch events that either all or any of the tags
     *
     * @param tags - The tags to match
     * @param numEvents - The number of events to get
     * @param matchAll - Whether to match all, or any of the tags
     * @return ArrayList of numEvents events.
     */
    /*
    public static ArrayList<Event> getEventsByTags(ArrayList<String> tags, int numEvents,
                                                   boolean matchAll) {
        final ArrayList<Event> events = new ArrayList<Event>(numEvents);
        // Get numEvents events from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        query.addDescendingOrder("date"); // sort by date most recent first
        if ( matchAll ) {
            query.whereContainsAll("tags", tags); // match all tags
        } else {
            for (int i = 0; i < tags.size(); i++) {
                query.whereContains("tags", tags.get(i)); // match any tag
            }
        }
        query.setLimit(numEvents);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userEvents, ParseException e) {
                if (e == null) {
                    Log.d("EventsBundler", "Retrieved " + userEvents.size()
                            + " events");
                    for (ParseObject ev : userEvents) {
                        String title = ev.getString("title");
                        String loc = ev.getString("loc");
                        Date date = ev.getDate("date");
                        String desc = ev.getString("description");
                        String id = ev.getString("objectId");
                        events.add(new Event(title, loc, date, desc, id));
                    }
                } else {
                    Log.d("EventsBundler", "Error: " + e.getMessage());
                }
            }
        });
        Log.d("EventsBundler", "Size of events before return: " + events.size());
        return events;
    }

*/

    /**
     * getEvent Returns an Event object created from the entry in the db
     *          corresponding to the input id
     *
     * @param id - identifier of Event object
     * @return Event object
     */
    public static Event getEvent(final String id) throws ParseException {

        // Emergency!!! ID is invalid, so create and return a dummy event.
        if (id.equals("ids of march")) {
            return new Event();
        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        ParseObject parEvent = null;
        try {
            parEvent = query.get(id);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        String title = parEvent.getString("title");
        String loc = parEvent.getString("loc");
        Date date = parEvent.getDate("date");
        String desc = parEvent.getString("description");
        return new Event(title, loc, date, desc, id);
    }

}
