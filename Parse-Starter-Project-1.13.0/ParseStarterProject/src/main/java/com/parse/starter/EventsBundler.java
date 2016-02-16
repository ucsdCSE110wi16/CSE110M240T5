package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
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
 */
public final class EventsBundler {

    /** Fetches numEvents events from the database, largest date value first.
     *
     * @param numEvents - The number of events to get
     * @return ArrayList of numEvents most recent events.
     */
    public static ArrayList<Event> recentEvents(int numEvents) {
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
                        String id = ev.getString("objectId");
                        events.add(new Event(title, loc, date, desc, id));
                    }
                }
                else {
                    Log.d("event", "Error: " + e.getMessage());
                }
            }
        });
        return events;
    }

    public static ArrayList<Event> testEvents(int numEvents){
        final ArrayList<Event> events = new ArrayList<Event>(numEvents);

        for(int i = 0;i<3;i++){
            events.add(new Event("We","Are,",new Date(),"Wat","Wattt"));
        }
        return events;
    }

    /** getEvent NEEDS TO BE IMPLEMENTED
     *
     * @param id - identifier of Event object
     * @return Event object
     */
    public static Event getEvent(String id) {
        Event example = new Event("Seance", "Red shoe", new Date(), "Dale a tu cuerpo alegria Macarena Que tu cuerpo es pa' darle alegria y cosa buena Dale a tu cuerpo alegria, Macarena Hey Macarena Dale a tu cuerpo alegria Macarena Que tu cuerpo es pa' darle alegria y cosa buena Dale a tu cuerpo alegria, Macarena Hey Macarena Macarena tiene un novio que se llama Que se llama de apellido Vitorino Que en la jura de bandera el muchacho Se la dio con dos amigos Macarena tiene un novio que se llama Que se llama de apellido Vitorino Y en la jura de bandera el muchacho Se la dio con dos amigos Dale a tu cuerpo alegria Macarena Que tu cuerpo es pa' darle alegria y cosa buena Dale a tu cuerpo alegria, Macarena Hey Macarena", "testID");
        return example;
    }
}
