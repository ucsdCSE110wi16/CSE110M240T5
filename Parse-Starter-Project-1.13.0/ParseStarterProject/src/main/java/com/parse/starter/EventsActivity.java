package com.parse.starter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.parse.ParseException;

import java.util.List;

/**
 * This class is the Activity where the Event Rows are displayed.
 * This activity is reached when CHECKOUT EVENTS is pressed.
 * Created by: Jenny, Ryan, Alex
 */

public class EventsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvEvents;
    private List<Event> vents;
    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);

        // Set up and load the recycler view
        rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        vents = null;
        try {
            vents = EventsBundler.recentEvents(100);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new EventsAdapter(vents);
        rvEvents.setAdapter(adapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        // Set up the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                try {
                    vents = EventsBundler.recentEvents(100);
                    vents.add(0, new Event()); // for testing purposes since AddNewActivity not working.
                    // once it's working, must test that new events are put on the top
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.addAll(vents);
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
