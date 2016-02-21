package com.parse.starter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;

import java.util.Date;

import static android.app.PendingIntent.getActivity;

/**
 * Activity that appears after clicking a reply button in EventsActivity.
 * Shows details of an Event and opportunity to contact host and eventually RSVP.
 * Created by: Jenny, Alex, Hans, Johnathan
 */
public class ExpandDetailsActivity extends AppCompatActivity {

    Event finalEvent;
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
        Event example = null;
        try {
            example = EventsBundler.getEvent(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (example == null) {
            example = new Event();
        }
        this.finalEvent=example;

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
        // uncomment after creator problem resolved
        //String host = "Hosted by: " + example.getCreator().getUsername();
        myTV6.setText("Hosted By: Liza Minnelli");

        Button rsvpButton = (Button) findViewById(R.id.buttonRSVP);
        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment size from inside an inner anonymous class. tricky tricky tricky
                Event event = new Event();
                event.updateSize();
                Toast.makeText(ExpandDetailsActivity.this, "lol I don't really do anything yet",
                        Toast.LENGTH_LONG).show();
            }
        });

        Button contactButton = (Button) findViewById(R.id.buttonContact);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExpandDetailsActivity.this, finalEvent.getContact(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
