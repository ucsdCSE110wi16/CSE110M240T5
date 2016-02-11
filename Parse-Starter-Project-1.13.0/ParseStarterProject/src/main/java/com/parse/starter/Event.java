package com.parse.starter;

import com.parse.ParseUser;
import java.util.ArrayList;

/** Class to bundle information about an event inside an object.
 * Created by Johnathan on 1/31/2016.
 * Merged with EventsForLoading.java by Jenny on 2/11/2016.
 */
public class Event {

    private ParseUser creator;
    private String title;
    private String description;
    private String userDefinedLocation;

    // just added
    private String date;
    private String details;

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


    public Event(String name, String loc, String date, String des) {
        this.title = name;
        this.userDefinedLocation = loc;
        this.date = date;
        this.description = des;
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

    public static ArrayList<Event> createEventsList(int size) {
        ArrayList<Event> events = new ArrayList<Event>(size);

        Event event1 = new Event("Pop up restaurant", "CSE Basement", "TODAY!", "");
        Event event2 = new Event("Pity party, need a +1", "RIMAC", "Feb 11", "");
        Event event3 = new Event("Basketball game COME THRU", "My house", "2/11", "");

        events.add(event1);
        events.add(event2);
        events.add(event3);

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

    public String getDate() { return this.date; }

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
