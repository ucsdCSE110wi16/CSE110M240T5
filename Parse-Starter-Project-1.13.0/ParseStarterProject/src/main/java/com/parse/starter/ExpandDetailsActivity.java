package com.parse.starter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
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
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * onCreate -  load the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {

        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        setContentView(R.layout.activity_expand_details);

        // Access Event object
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        this.finalEvent = null;
        try {
            this.finalEvent = EventsBundler.getEvent(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (this.finalEvent == null) {
            this.finalEvent = new Event();
        }
        this.finalEvent.validateMe();

        TextView myTV = (TextView) findViewById(R.id.tvDetailsEventName);
        myTV.setText(this.finalEvent.getTitle());

        TextView myTV2 = (TextView) findViewById(R.id.tvDetailsDate);
        myTV2.setText(this.finalEvent.getDate()); // TODO make prettier

        TextView myTV3 = (TextView) findViewById(R.id.tvDetailsLocation);
        myTV3.setText(this.finalEvent.getUserDefinedLocation());

        TextView myTV4 = (TextView) findViewById(R.id.tvDetailsDescription);
        myTV4.setText(this.finalEvent.getDescription());

        final TextView myTV5 = (TextView) findViewById(R.id.tvDetailsAttendees);
        String sz = "Number of Attendees: " + this.finalEvent.getSize() + "/" +
                this.finalEvent.getCapacity();
        myTV5.setText(sz);

        String host;
        TextView myTV6 = (TextView) findViewById(R.id.tvDetailsHostName);
        host = this.finalEvent.getHostName();

        myTV6.setText("Hosted by: " + host);

        TextView myTV7 = (TextView) findViewById(R.id.tvDetailsTags);
        myTV7.setText("Tags: " + (this.finalEvent.getTags()).toString());

        final Button rsvpButton = (Button) findViewById(R.id.buttonRSVP);
        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser pu = ParseUser.getCurrentUser();
                ArrayList<ParseUser> guests = finalEvent.getAttendees();

                if (guests.contains(pu))
                {
                    // Remove user
                    guests.remove(pu);
                    finalEvent.setAttendees(guests);
                    //Remove user and button change colour back to original and "Join"
                    rsvpButton.setBackgroundColor(Color.parseColor("#65ee83"));
                    rsvpButton.setText((CharSequence) "Join");
                }
                else
                {
                    // Add user
                    if (finalEvent.getSize() < finalEvent.getCapacity())
                    {
                        guests.add(pu);
                        finalEvent.setAttendees(guests);
                        //change button colour to RSVPed Colour and text to "Leave"
                        rsvpButton.setBackgroundColor(Color.parseColor("#FF7878"));
                        rsvpButton.setText((CharSequence) "Leave");
                    }
                    else
                    {
                        // Event is maxed out
                        String st = "Sorry, event is at capacity.";
                        Toast.makeText(ExpandDetailsActivity.this, st, Toast.LENGTH_SHORT).show();
                    }
                }

                // Update database
                try {
                    EventsBundler.updateRSVP(finalEvent.getID(), guests);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Update GUI
                String newsz = "Number of Attendees: " + finalEvent.getSize() +
                        "/" + finalEvent.getCapacity();
                myTV5.setText(newsz);

            }
        });

        Button contactButton = (Button) findViewById(R.id.buttonContact);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finalEvent.validateMe();
                String phone = finalEvent.getContact();
                String body = "Hey fam, can I slide thru to " +
                        finalEvent.getTitle() + "?";

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + phone));
                sendIntent.putExtra("sms_body", body);
                ExpandDetailsActivity.this.startActivity(sendIntent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExpandDetails Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.parse.starter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ExpandDetails Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.parse.starter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
