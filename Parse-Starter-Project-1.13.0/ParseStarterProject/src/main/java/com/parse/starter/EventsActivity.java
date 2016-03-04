package com.parse.starter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the Activity where the Event Rows are displayed.
 * This activity is reached when CHECKOUT EVENTS is pressed.
 * Created by: Jenny, Ryan, Alex
 */

public class EventsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvEvents;
    private android.support.v7.widget.SearchView search;
    private List<Event> vents = null;
    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);

        // Set up and load the recycler view
        rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
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
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                try {
                    vents = EventsBundler.recentEvents(100);
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

        // Set up the search view
        search = (android.support.v7.widget.SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO: separate terms in query by comma and feed into a new arraylist
                ArrayList<String> tags = new ArrayList<String>();
                tags.add(query);
                List<Event> searchResults = EventsBundler.getEventsByTags(tags, 100, true);
                adapter.clear();
                adapter.addAll(searchResults);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.clear();
                try {
                    vents = EventsBundler.recentEvents(100);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.addAll(vents);
                return true;
            }
        });

        search.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.clear();
                try {
                    vents = EventsBundler.recentEvents(100);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.addAll(vents);
                return true;
            }
        });
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
