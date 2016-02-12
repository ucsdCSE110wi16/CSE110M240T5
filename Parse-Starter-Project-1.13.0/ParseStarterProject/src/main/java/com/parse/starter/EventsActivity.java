package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

//    public static void viewEventDetails(View eventV) {
//        startDetailedEventActivity(eventV);
//    }
//
//    protected void startDetailedEventActivity(View eventV) {
//        Intent intent = new Intent(this, ExpandDetailsActivity.class);
//        startActivity(intent);
//    }

    /**
     * TODO
     * @param v
     */
    public void replyButtonOnClick(View v) {
        Intent intent = new Intent(this, ExpandDetailsActivity.class);
        startActivity(intent);
    }

}
