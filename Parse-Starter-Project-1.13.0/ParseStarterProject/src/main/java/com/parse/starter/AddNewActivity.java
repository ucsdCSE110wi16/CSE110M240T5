package com.parse.starter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseObject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    Button bSubmit;
    EditText actName, actLoc, actTime, actTags;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        setContentView(R.layout.activity_add_new);

        actName = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyName);
        actLoc = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyLoc);
        actTime = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyTime);
        actTags = (EditText) findViewById(R.id.et_NEWACTIVITY_tags);

        bSubmit = (Button) findViewById(R.id.bt_NEWACTIVITY_submit);
        bSubmit.setOnClickListener(this);
    }

    protected void onStart() {
        mGoogleApiClient.connect(); // connect to Google API
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect(); // disconnect from Google API
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        boolean validInputForm = true;
        //Check activity name
        String title = actName.getText().toString();
        if (title.matches("")) {
            validInputForm = false;
        }
        //Check activity location
        String loc = actLoc.getText().toString();
        if (loc.matches("")) {
            validInputForm = false;
        }
        //Check activity time
        String time = actTime.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
        }
        if (!validInputForm) {
            Toast.makeText(this,
                    "Your event is missing some information",
                    Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.bt_NEWACTIVITY_submit && validInputForm) {
            //Send data to the parse Database.
            System.out.println("Pushing new UserEvent to database");
            ParseObject userEvent = new ParseObject("UserEvent");
            userEvent.put("title", title);
            userEvent.put("loc", loc);
            userEvent.put("time", time);
            userEvent.saveInBackground();
            //TODO Need a way to update the events
            finish();
        }
    }


    /**
     * TODO do something
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {

    }

    /**
     * TODO do something
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * TODO do something
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
