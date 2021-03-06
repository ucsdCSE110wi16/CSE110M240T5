package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
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
    private String date;
    private String id;
    private int capacity;
    ArrayList<ParseUser> attendees;
    private String contact;
    private ArrayList<String> tags;
    private String hostname;

    /** Constructor to create a dummy event for when event attempted to be
     *  accessed is null. (testing)
     */
    public Event() {
        creator = null;
        title = "Dummy Convention";
        description = "Sorry your event does not exist.";
        userDefinedLocation = "Rubio's";
        date = "April 3, 2016";
        id = "ids of march";
        capacity = 3;
        attendees = new ArrayList<ParseUser>();
        contact = "831-408-3232";
        hostname = "UCSD";
    }

    /** Constructor with bare minimum information required for an event
     *
     * @param title - The title of the event
     * @param creator - The user who has created this event
     */
    public Event(String title, ParseUser creator) {
        this();
        this.title = title;
        this.creator = creator;
    }



    public Event(String title, String loc, String date, String des, String id,
String contact, int capacity, ParseUser creator, ArrayList<String> tags, String hostname) {
        this.creator = creator;
        this.title = title;
        this.description = des;
        this.userDefinedLocation = loc;
        this.date = date;
        this.id = id;
        this.capacity = capacity;
        this.attendees = new ArrayList<ParseUser>();
        this.contact = contact;
        this.tags = tags;
        this.hostname = hostname;
    }

/*    *//** Add or remove a user to the attendee list
     *  Note: attendees and size changed here just for display to user
     * @return true for added, false for not added (for whatever reason)
     *//*
    public boolean toggleAttendance(ParseUser pu) {

        // Event at full capacity already
        if (!(size < capacity)) {
            return false;
        }
        // Already attending
        if (this.attendees.contains(pu)) {
            this.size--;
            this.attendees.remove(pu);
            // Update parse database of changes to size & attendees
            try {
                // Pass in event ID and user ID
                EventsBundler.updateRSVP(this.getID(), pu);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }
        else {
            this.size++;
            this.attendees.add(pu);
            // Update parse database of changes to size & attendees
            try {
                EventsBundler.updateRSVP(this.getID(), pu);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return true;
        }
    }*/

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

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return (this.attendees.size());
    }

    public ArrayList<ParseUser> getAttendees() {
        return this.attendees;
    }

    public String getContact() {
        return this.contact;
    }

    public ArrayList<String> getTags() {
        return tags;
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

    public void setContact(String c) {
        this.contact = c;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public void setAttendees(ArrayList<ParseUser> attendees) {
        this.attendees = attendees;
    }

    public String getHostName() {
        return hostname;
    }

    /** Null checker */
    public boolean validateMe() {
        boolean changed = false;
        if (this.creator == null) {
            this.creator = new ParseUser();
            this.creator.setUsername("COOL USER");
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
            date = (new Date()).toString();
            changed = true;
        }
        if (this.id == null) {
            this.id = "ids of march";
            changed = true;
        }

        if (this.capacity == 0) {
            this.capacity = 100;
            changed = true;
        }
        if (this.attendees == null) {
            this.attendees = new ArrayList<ParseUser>();
            changed = true;
        }
        if (this.contact == null) {
            this.contact = "8675309";
            changed = true;
        }

        if (hostname == null) {
            hostname = "";
            changed = true;
        }
        if (hostname.equals("")) {
            try {
                hostname = this.getCreator().getUsername();
            }
            catch (Exception e) {
                hostname = "UCSD";
            }
            changed = true;
        }

        return changed;
    }


}
