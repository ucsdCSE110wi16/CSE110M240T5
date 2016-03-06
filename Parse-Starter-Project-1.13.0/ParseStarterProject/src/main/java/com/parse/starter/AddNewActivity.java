package com.parse.starter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.Manifest;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


/**
 * Created by: Alex, Johnathan
 */
public class AddNewActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    private static int MY_PERMISSIONS_REQUEST_COURSE_LOCATION;
    Button bSubmit;
    EditText actName, actLoc, actTime, actTags, actContact, actCapacity, actDesc;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

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
        actContact = (EditText) findViewById(R.id.et_NEWACTIVITY_contact);
        actCapacity = (EditText) findViewById(R.id.et_NEWACTIVITY_capacity);
        actDesc = (EditText) findViewById(R.id.et_NEWACTIVITY_desc);
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

        //Check activity contact
        String contact = actContact.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
        }

        //Check activity capacity
        String capacity = actCapacity.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
        }

        //Check activity description
        String desc = actDesc.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
        }

        //Check activity tags
        String tags = actTags.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
        }

        if (!validInputForm) {
            Toast.makeText(this,
                    "Your event is missing some information",
                    Toast.LENGTH_SHORT).show();
                    return;
        }

        if (v.getId() == R.id.bt_NEWACTIVITY_submit && validInputForm) {
            //Send data to the parse Database.
            System.out.println("Pushing new UserEvent to database");

            ParseACL acl = new ParseACL(); // access control list
            acl.setPublicReadAccess(true);

            /* Should always have user logged in here. */
            ParseUser currUser = ParseUser.getCurrentUser();
            ParseObject eventCreator = new ParseObject("EventCreator");
            if (currUser != null) { // TODO enforce login and remove
                eventCreator.put("username", currUser.getString("username"));
                /* eventCreator.put("id", currUser.getString("objectId"));
                TODO investigate why this is null */
                eventCreator.put("name", currUser.getString("name"));
                eventCreator.put("email", currUser.getString("email"));
                eventCreator.put("user", currUser); // for Event ctor
            }
            // Determine recent location for creator
            String creatorLat = null;
            String creatorLong = null;
            if (mLastLocation != null) {
                creatorLat = String.valueOf(mLastLocation.getLatitude());
                creatorLong = String.valueOf(mLastLocation.getLongitude());
            }
            if ( creatorLat != null && creatorLong != null ) {
                eventCreator.put("latitude", creatorLat);
                eventCreator.put("longitude", creatorLong);
            }
            eventCreator.setACL(acl);
            try {
                eventCreator.save();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Attempt to lookup address based on user input
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocationName(loc, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            double eventLat = 0;
            double eventLong = 0;
            if (addresses != null && addresses.size() > 0) {
                eventLat = addresses.get(0).getLatitude();
                eventLong = addresses.get(0).getLongitude();
            }

            // Create Parse Geo information so we can lookup this event later
            ParseGeoPoint geoPoint = new ParseGeoPoint(eventLat, eventLong);

            ParseObject userEvent = new ParseObject("UserEvent");
            userEvent.setACL(acl);
            userEvent.put("geoLocation", geoPoint); // result of lookup
            userEvent.put("loc", loc); // user defined location
            userEvent.put("title", title);
            userEvent.put("time", time);
            userEvent.put("description", desc);
            userEvent.put("contact", contact);

            userEvent.put("capacity", Integer.parseInt(capacity));
            userEvent.put("tags", tags);

            userEvent.put("size", 0); // number of attendees
            userEvent.put("creator", eventCreator);

            try {
                userEvent.save(); // consider not saving in background if not working
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //TODO Need a way to update the events
            finish();
        }
    }


    /** What to do once connected to GoogleApiClient
     *
     * Reference: http://developer.android.com/training/location/retrieve-current.html
     * https://developers.google.com/android/guides/setup
     * http://developer.android.com/training/location/index.html
     * http://developer.android.com/training/permissions/requesting.html
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        // Here, this is the current activity
        if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_COURSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_COURSE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
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


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO: take hourOfDay and minute and put into parse events

        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
