package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseException;

import java.util.List;

/**
 * This class is the Activity where the Event Rows are displayed.
 * This activity is reached when CHECKOUT EVENTS is pressed.
 * Created by: Jenny, Ryan, Alex
 */

public class EventsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
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
        List<Event> vents = null;
        try {
            vents = EventsBundler.recentEvents(eventsPerQuery);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Context context = getApplicationContext();
        EventsAdapter adapter = new EventsAdapter(vents);

        // Attach the adapter to the recyclerview to populate items
        rvEvents.setAdapter(adapter);

        // Set layout manager to position the items
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onStart();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }



}
