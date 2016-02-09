package com.parse.starter;

import java.util.Date;

/**
 * Created by jennywong on 2/8/16.
 */
public class EventsForLoading {

    private String eventName;
    private String location;
    private Date date;
    private String details;

    // Create empty parameter ctor

    public EventsForLoading(String eventNameP, String locationP, Date dateP, String detailsP) {
        this.eventName = eventNameP;
        this.location = locationP;
        this.date = dateP;
        this.details = detailsP;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static List<Contact> createContactsList(int numContacts) {
        List<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact("Person " + ++lastContactId, i <= numContacts / 2));
        }

        return contacts;
    }


}
