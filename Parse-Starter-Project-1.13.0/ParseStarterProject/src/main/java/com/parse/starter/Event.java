package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
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

    private ParseObject creator;
    private String title;
    private String description;
    private String userDefinedLocation;
    private Date date;
    private String id;
    private int size;
    private int capacity;
    ArrayList<ParseObject> attendees;
    private String contact;

    /** Constructor to create a dummy event for when event attempted to be
     *  accessed is null. (testing)
     */
    public Event() {
        creator = null;
        title = "Dummy Convention";
        description = "Sorry your event does not exist.";
        userDefinedLocation = "Rubio's";
        date = new Date();
        id = "ids of march";
        size = 0;
        capacity = 3;
        attendees = new ArrayList<ParseObject>();
        contact = "831-408-3232";
    }

    /** Constructor with bare minimum information required for an event
     *
     * @param title - The title of the event
     * @param creator - The user who has created this event
     */
    public Event(String title, ParseObject creator) {
        this();
        this.title = title;
        this.creator = creator;
    }


    public Event(String title, String loc, Date date, String des, String id,
String contact, int capacity, ParseObject creator) {
        this.creator = creator;
        this.title = title;
        this.description = des;
        this.userDefinedLocation = loc;
        this.date = new Date();
        this.id = id;
        this.size = 0;
        this.capacity = capacity;
        if (capacity ==0) // if capacity=0, set to default 50
            this.capacity = 50;
        this.attendees = new ArrayList<ParseObject>();
        this.contact = contact;
    }

    /** Add or remove a user to the attendee list
     *
     * @param pu The user to add/remove
     * @return true for added, false for not added (for whatever reason)
     */
    public boolean toggleAttendance(ParseObject pu) {
        // First attendee, need to initialize list
        if (attendees == null) {
            attendees = new ArrayList<ParseObject>();
        }
        // Already attending
        if (attendees.contains(pu)) {
            this.size--;
            attendees.remove(pu);
            // Update parse database of changes to size & attendees
            try {
                EventsBundler.updateRSVP(this.getID(), (String) pu.get("username"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }
        // Event at full capacity already
        if (!(size < capacity)) {
            return false;
        }
        // Event is not at capacity yet
        this.size++; // size changed just for display to the current user
        attendees.add(pu);
        // Update parse database of changes to size & attendees
        try {
            EventsBundler.updateRSVP(this.getID(), (String) pu.get("username"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public ParseObject getCreator() {
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

    public ArrayList<ParseObject> getAttendees() {
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

    /** Null checker */
    public boolean validateMe() {
        boolean changed = false;
        if (this.creator == null) {
            changed = true;
        }
        if (this.title == null) {
            this.title = "COOL TITLE";
            changed = true;
        }
        if (this.description == null) {
            this.description = "COOL DESCRIPTION";
            changed = true;
        }
        if (this.userDefinedLocation == null) {
            this.userDefinedLocation = "COOL LOCATION";
            changed = true;
        }
        if (this.date == null) {
            date = new Date();
            changed = true;
        }
        if (this.id == null) {
            this.id = "ids of march";
            changed = true;
        }
        if (this.size < 0) {
            this.size = 0;
            changed = true;
        }
        if (this.capacity == 0) {
            this.capacity = 100;
            changed = true;
        }
        if (this.attendees == null) {
            this.attendees = new ArrayList<ParseObject>();
            changed = true;
        }
        if (this.contact == null) {
            this.contact = "8675309";
            changed = true;
        }
        return changed;
    }

}
