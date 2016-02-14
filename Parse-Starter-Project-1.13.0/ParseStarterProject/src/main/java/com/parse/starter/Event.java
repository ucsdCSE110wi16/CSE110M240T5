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

/** Class to bundle information about an event inside an object.
 * Created by Johnathan on 1/31/2016.
 * Merged with EventsForLoading.java by Jenny on 2/11/2016.
 */
public class Event {

    private ParseUser creator;
    private String title;
    private String description;
    private String userDefinedLocation;
    private Date date;
    private int id; // TODO generate unique event ids for events
    private int size;
    private int capacity;
    ArrayList<ParseUser> attendees;
    private String altContact;

    /** Constructor with bare minimum information required for an event
     *
     * @param title - The title of the event
     * @param creator - The user who has created this event
     */
    public Event(String title, ParseUser creator) {
        this.title = title;
        this.creator = creator;
    }


    public Event(String title, String loc, Date date, String des) {
        this.title = title;
        //this.creator = creator;
        this.userDefinedLocation = loc;
        this.date = date;
        this.description = des;
        this.size = 0;
    }

    /** Returns whether or not the event has a limit on the number of
     * attendees.
     *
     * @return True if event is capacity limited
     *         False otherwise
     */
    public boolean hasCapacity() {
        if ( this.capacity > 0 ) {
            return true;
        }
        return false;
    }

    /** Add a user to the attendee list
     *
     * @param toAdd The user to add
     * @return Whether or not the add was successful
     */
    public boolean addAttendee(ParseUser toAdd) {
        if (attendees == null) {
            attendees = new ArrayList<ParseUser>();
        }
        if ( this.hasCapacity() ) {
            if (!(size < capacity)) {
                return false;
            }
        }
        attendees.add(toAdd);
        return true;
    }

    // TODO change to more descriptive name
    // maybe "getAllEvents
    public static ArrayList<Event> createEventsList(int numEvents) {
        final ArrayList<Event> events = new ArrayList<Event>(numEvents);
        // Get numEvents events from the database
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserEvent");
        query.addDescendingOrder("date"); // sort by date most recent first
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> userEvents, ParseException e) {
                if (e == null) {
                    Log.d("event", "Retrieved " + userEvents.size()
                            + " events");
                    for (ParseObject ev : userEvents) {
                        String title = ev.getString("title");
                        String loc = ev.getString("loc");
                        Date date = ev.getDate("date");
                        String desc = ev.getString("description");
                        events.add(new Event(title, loc, date, desc));
                    }
                }
                else {
                    Log.d("event", "Error: " + e.getMessage());
                }
            }
        });

        return events;
    }


    ///// Getters /////

    public String getTitle() {
        return this.title;
    }

    public ParseUser getCreator() {
        return this.creator;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUserDefinedLocation() {
        return this.userDefinedLocation;
    }

    public int getID() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public ArrayList<ParseUser> getAttendees() {
        return this.attendees;
    }

    public String getAltContact() {
        return this.altContact;
    }

    public Date getDate() { return this.date; }

    ///// Setters /////

    public void setTitle(String t) {
        this.title = t;
    }

    public void setDescription(String d) {
        this.description = d;
    }

    public void setUserDefinedLocation(String loc) {
        this.userDefinedLocation = loc;
    }

    public void setCapacity(int c) {
        this.capacity = c;
    }

    public void setAltContact(String c) {
        this.altContact = c;
    }
}