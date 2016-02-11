package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        // Lookup the recyclerview in activity layout
        RecyclerView rvEvents = (RecyclerView) findViewById(R.id.rvEvents);
        // Create adapter passing in the sample user data
        EventsAdapter adapter = new EventsAdapter(EventsForLoading.createEventsList(3));
        // Attach the adapter to the recyclerview to populate items
        rvEvents.setAdapter(adapter);
        // Set layout manager to position the items
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

}
