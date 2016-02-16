package com.parse.starter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

/**
 * TODO
 */
public class ExpandDetailsActivity extends AppCompatActivity {

    /**TODO
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Access Event object -> load instance variables below
        // Maybe we can throw all of this in a method. pass in object to update fields.
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");

        Event example = EventsBundler.getEvent(id);

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
