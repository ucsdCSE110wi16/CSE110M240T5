import com.parse.ParseUser;

import java.util.ArrayList;

/** Class to bundle information about an event inside an object.
 * Created by Johnathan on 1/31/2016.
 */
public class Event {

    private ParseUser creator;
    private String title;
    private String description;
    private String userDefinedLocation;
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
