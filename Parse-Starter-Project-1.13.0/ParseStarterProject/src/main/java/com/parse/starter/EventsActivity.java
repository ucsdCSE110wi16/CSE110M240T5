package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * This class is the Activity where the Event Rows are displayed.
 * This activity is reached when CHECKOUT EVENTS is pressed.
 */
// TODO fix events not loading after user presses back
public class EventsActivity extends AppCompatActivity {

    public static final int eventsPerQuery = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_events);
        // Lookup the recyclerview in activity layout
        RecyclerView rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        // Create adapter passing in the sample user data
        List<Event> vents = EventsBundler.recentEvents(eventsPerQuery);
        Context context = getApplicationContext();
        EventsAdapter adapter = new EventsAdapter(vents);
        // Attach the adapter to the recyclerview to populate items
        System.out.println("Size of event list: " + vents.size());
        for (Event e : vents) {
            System.out.println(e.getTitle());
        }
        rvEvents.setAdapter(adapter);
        // Set layout manager to position the items
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}
