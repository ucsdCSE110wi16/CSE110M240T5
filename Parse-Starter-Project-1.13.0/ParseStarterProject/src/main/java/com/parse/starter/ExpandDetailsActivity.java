package com.parse.starter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Activity that appears after clicking a reply button in EventsActivity.
 * Shows details of an Event and opportunity to contact host and eventually RSVP.
 * Created by: jennywong
 */
public class ExpandDetailsActivity extends AppCompatActivity {

    /**
     * onCreate -  load the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details);

        // Access Event object
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        Event example = EventsBundler.getEvent(id);

        // Contact button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact: 1-800-HotlineBling", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Capacity and contact info need to be accounted for!! Please ask for these two pieces of
        // info in AddNewActivity
        example.setCapacity(10);

        TextView myTV = (TextView) findViewById(R.id.tvDetailsEventName);
        myTV.setText(example.getTitle());

        TextView myTV2 = (TextView) findViewById(R.id.tvDetailsDate);
        myTV2.setText(example.getDate().toString()); // TODO make prettier

        TextView myTV3 = (TextView) findViewById(R.id.tvDetailsLocation);
        myTV3.setText(example.getUserDefinedLocation());

        TextView myTV4 = (TextView) findViewById(R.id.tvDetailsDescription);
        myTV4.setText(example.getDescription());

        TextView myTV5 = (TextView) findViewById(R.id.tvDetailsAttendees);
        String sz = "Number of Attendees: " + example.getSize() + "/" + example.getCapacity();
        myTV5.setText(sz);

        TextView myTV6 = (TextView) findViewById(R.id.tvDetailsHostName);
//        String host = "Hosted by: " + example.getCreator().toString();
        myTV6.setText("Hosted By: Jenny");
    }

}
