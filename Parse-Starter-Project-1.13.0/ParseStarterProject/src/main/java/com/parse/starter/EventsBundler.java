package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
        query.addDescendingOrder("updatedAt"); // sort by date most recent first
        // query.whereNear("location", <user ParseGeoPoint goes here>);
        query.setLimit(numEvents);
        List<ParseObject> userEvents = query.find();
        for (ParseObject ev : userEvents) {
            String title = ev.getString("title");
            String loc = ev.getString("loc");
            Date date = ev.getDate("date");
            String desc = ev.getString("description");
            String id = ev.getObjectId();
            String contact = ev.getString("contact");
            String tags = ev.getString("tags");
            int capacity = ev.getInt("capacity");
            ParseUser pu = ev.getParseUser("user");

            Event newEv = new Event(title, loc, date, desc, id, contact, capacity, pu, tags);
            newEv.validateMe();
            events.add(newEv);
        }
        return events;
    }

    public static List<Event> testEvents(int numEvents) {
        List<Event> events = new ArrayList<Event>(numEvents);

        events.add(new Event("title1", "loc1", new Date(), "desc1", "111", "contact1", 1, null, "tags"));
        events.add(new Event("title2", "loc2,", new Date(), "desc2", "222", "contact1", 1, null, "tags"));
        events.add(new Event("title3", "loc3,", new Date(), "desc3", "333", "contact1", 1, null, "tags"));
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
    // TODO update and add location search
    public static ArrayList<Event> getEventsByTags(ArrayList<String> tags, int numEvents,
                                                   boolean matchAll) {

        final ArrayList<Event> events = new ArrayList<Event>(numEvents);
        Event c = new Event();
        c.setTitle("mcsearch");
        events.add(c);
        /*
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
        */
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

        // ID is invalid, so create and return a dummy event.
        if (id==null || id.equals("ids of march")) {
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
        String contact = parEvent.getString("contact");
        int capacity = parEvent.getInt("capacity");
        String tags = parEvent.getString("tags");
        ParseUser pu = parEvent.getParseUser("creator");

        Event newEv = new Event(title, loc, date, desc, id, contact, capacity, pu, tags);
        newEv.validateMe();
        return newEv;
    }

    /**
     * Increase the size of the event(number of attendees)
     * TODO: Decrease size and remove user from attendees, remove size<cap check
     * @param eventId - The id of the event to update
     * @param userId - The id of the user to add to the attendees list TODO update
     * @return Boolean - True if successfully updated
     *                   False if unsuccessful
     */
    public static boolean updateRSVP(String eventId, String userId) throws ParseException {
        // Get numEvents events from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        ParseObject parEvent = null;
        try {
            parEvent = query.get(eventId);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        if (parEvent != null) {
            int cap = parEvent.getInt("capacity");
            int size = parEvent.getInt("size");
            if ( size < cap ) {
                parEvent.put("size",++size);
                parEvent.save();
                return true;
            }
        }
        return false;
    }

}
