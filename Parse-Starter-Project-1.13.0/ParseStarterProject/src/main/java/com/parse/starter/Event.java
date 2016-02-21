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
 * Created by Johnathan, Jenny
 */
public class Event {

    private ParseUser creator;
    private String title;
    private String description;
    private String userDefinedLocation;
    private Date date;
    private String id;
    private int size;
    private int capacity;
    ArrayList<ParseUser> attendees;
    private String contact;

    /** Constructor to create a dummy event for when event attempted to be
     *  accessed is null. (testing)
     */
    public Event() {
        creator = null;
        title = "Dummy Convention";
        description = "Free ventriloquism lessons. Sorry your event does not exist.";
        userDefinedLocation = "Rubio's";
        date = new Date();
        id = "ids of march";
        size = 0;
        capacity = 3;
        attendees = new ArrayList<ParseUser>();
        contact = "831-408-3232";
    }

    /** Constructor with bare minimum information required for an event
     *
     * @param title - The title of the event
     * @param creator - The user who has created this event
     */
    public Event(String title, ParseUser creator) {
        super();
        this.title = title;
        this.creator = creator;
    }


    public Event(String title, String loc, Date date, String des, String id) {
        this.creator = null; // need to set in AddNewActivity
        this.title = title;
        this.description = des;
        this.userDefinedLocation = loc;
        this.date = new Date();
        this.id = id;
        this.size = 0;
        this.capacity = 10; // need AddNewActivity form
        this.attendees = new ArrayList<ParseUser>();
        this.contact = "Contact "+title; // need AddNewActivity form
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

    @Override
    public String toString() {
        return "EventID: " + getID() + "; Title: " + getTitle();
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

    public String getID() {
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

    public String getContact() {
        return this.contact;
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

    public void setContact(String c) {
        this.contact = c;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public void updateSize() { this.size++;
    }
}