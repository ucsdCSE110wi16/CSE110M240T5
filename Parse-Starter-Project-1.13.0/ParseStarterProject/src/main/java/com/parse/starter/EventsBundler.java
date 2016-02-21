package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Johnathan on 2/13/2016.
 *
 * This class interfaces with the database to Bundle events into containers.
 * TODO investigate issue with database cutting in and out
 * TODO investigate issue with null events
 */
public final class EventsBundler {

    /**
     * Fetches numEvents events from the database, largest date value first.
     *
     * @param numEvents - The number of events to get
     * @return ArrayList of numEvents most recent events.
     */
    public static ArrayList<Event> recentEvents(int numEvents) {
        final ArrayList<Event> events = new ArrayList<Event>(numEvents);
        // Get numEvents events from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        query.addDescendingOrder("date"); // sort by date most recent first
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
        Log.d("EventsBundler", "Size of events before return: "+events.size());
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

    public static ArrayList<Event> testEvents(int numEvents) {
        final ArrayList<Event> events = new ArrayList<Event>(numEvents);

        events.add(new Event("title1", "loc1", new Date(), "desc1", "111"));
        events.add(new Event("title2", "loc2,", new Date(), "desc2", "222"));
        events.add(new Event("title3", "loc3,", new Date(), "desc3", "333"));
        return events;
    }

    /**
     * getEvent Returns an Event object created from the entry in the db
     *          corresponding to the input id
     * Created by: Johnathan Lutzke and Jenny Wong
     *
     * @param id - identifier of Event object
     * @return Event object
     */
    public static Event getEvent(final String id) {
        /*
        final Event[] ev = new Event[1];
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parEvent, ParseException e) {
                if (e == null) {
                    Log.d("event", "Retrieved event id: " + id);
                    String title = parEvent.getString("title");
                    String loc = parEvent.getString("loc");
                    Date date = parEvent.getDate("date");
                    String desc = parEvent.getString("description");
                    String id = parEvent.getString("objectId");
                    ev[0] = new Event(title, loc, date, desc, id);

                } else {
                    Log.d("event", "Error: " + e.getMessage());
                    ev[0] = null;
                }
            }
        });
        return ev[0]; */
        return new Event(id, id, new Date(), id, id);
    }

}
