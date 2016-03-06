package com.parse.starter;

import android.content.Intent;
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

import com.parse.ParseException;
import com.parse.ParseUser;

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
    }

    @Override
    protected void onStart() {

        super.onStart();
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
        try {
            host = this.finalEvent.getCreator().getUsername();
        } catch (Exception pe) {
            host = "UCSD";
        }
        myTV6.setText("Hosted by: " + host);

        TextView myTV7 = (TextView) findViewById(R.id.tvDetailsTags);
        myTV7.setText("Tags: "+(this.finalEvent.getTags()).toString());

        Button rsvpButton = (Button) findViewById(R.id.buttonRSVP);
        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ok = finalEvent.toggleAttendance(ParseUser.getCurrentUser());

                String newsz = "Number of Attendees: " + finalEvent.getSize() +
                        "/" + finalEvent.getCapacity();
                myTV5.setText(newsz);

/*                String st = "RSVP successful";
                if (!ok) {
                    st = "unRSVP'd or event full";
                }
                Toast.makeText(ExpandDetailsActivity.this, st, Toast.LENGTH_SHORT).show();
*/
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
    }
}
