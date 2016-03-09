package com.parse.starter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by: Alex, Johnathan, Jenny
 */
public class AddNewActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    private static int MY_PERMISSIONS_REQUEST_COURSE_LOCATION;
    Button bSubmit;
    static EditText actName, actLoc, actDate, actTime, actTags, actContact, actCapacity, actDesc;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    //static Date date;
    static Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(AppIndex.API).build();
        }

        setContentView(R.layout.activity_add_new);

        actName = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyName);
        actLoc = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyLoc);
        actDate = (EditText) findViewById(R.id.et_NEWACTIVITY_activityDate);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddNew Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.parse.starter/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    protected void onStop() {
        mGoogleApiClient.disconnect(); // disconnect from Google API
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddNew Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.parse.starter/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
    }

    @Override
    public void onClick(View v) {
        Date dt = new Date(calendar.getTimeInMillis());
        boolean validInputForm = true;
        int phoneNumberLength = 10;

        //Check activity name
        String title = actName.getText().toString();
        if (title.matches("")) {
            validInputForm = false;
            Toast.makeText(this,
                    "Wait, what's your event called?",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Check activity date
        String date = actDate.getText().toString();
        if (date.matches("")) {
            validInputForm = false;
            Toast.makeText(this,
                    "What day does this happen?",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Check activity time
        String time = actTime.getText().toString();
        if (time.matches("")) {
            validInputForm = false;
            Toast.makeText(this,
                    "So, what time?",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Check activity location
        String loc = actLoc.getText().toString();
        if (loc.matches("")) {
            validInputForm = false;
            Toast.makeText(this,
                    "But where is it?",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Check activity tags
        String tags = actTags.getText().toString();
        ArrayList<String> tagsArr = new ArrayList<String>(Arrays.asList(tags.split(",")));
        if(tagsArr.size() < 3) {
            validInputForm = false;
            Toast.makeText(this,
                    "Please enter at least 3 tags.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //Check activity contact
        String contact = actContact.getText().toString();
        if ((contact.matches("")) || (contact.length() < phoneNumberLength)) {
            validInputForm = false;
            Toast.makeText(this,
                    "Can I get yo numba?",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Check activity capacity
        String capacity = actCapacity.getText().toString();
        if (capacity.matches("") || capacity.matches("0")) {
            validInputForm = false;
            Toast.makeText(this,
                    "How many people can come?",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Check activity description
        String desc = actDesc.getText().toString();
        if (desc.matches("")) {
            validInputForm = false;
            Toast.makeText(this,
                    "Briefly describe your event!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        /*
        if (!validInputForm) {
            Toast.makeText(this,
                    "Your event is missing some information",
                    Toast.LENGTH_SHORT).show();
                    return;
        }
        */

        if (v.getId() == R.id.bt_NEWACTIVITY_submit && validInputForm) {
            //Send data to the parse Database.
            System.out.println("Pushing new UserEvent to database");

            ParseACL acl = new ParseACL(); // access control list
            acl.setPublicReadAccess(true);
            acl.setPublicWriteAccess(true);

            /* Should always have user logged in here. */
            ParseUser currUser = ParseUser.getCurrentUser();

            // Determine recent location for creator
            String creatorLat = null;
            String creatorLong = null;
            if (mLastLocation != null) {
                creatorLat = String.valueOf(mLastLocation.getLatitude());
                creatorLong = String.valueOf(mLastLocation.getLongitude());
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
            userEvent.put("date", date+"\n"+time);
            userEvent.put("description", desc);
            userEvent.put("contact", contact);
            userEvent.put("capacity", Integer.parseInt(capacity));
            userEvent.put("tags", tagsArr);
            userEvent.put("creator", currUser);
            userEvent.put("hostname", currUser.getUsername());

            ArrayList<ParseUser> attendees =
                    new ArrayList<ParseUser>(Integer.parseInt(capacity));
            userEvent.put("attendees", attendees);

            try {
                userEvent.save(); // consider not saving in background if not working
            } catch (ParseException e) {
                e.printStackTrace();
            }
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


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);
            actDate.setText(sdf.format(calendar.getTime()));
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
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
            // Do something with the time chosen by the user
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            // Put in 12 hr mode
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String state = " AM";
            if (hour > 12){
                hour -=12;
                state = " PM";
            }
            if(hour==12) {
                state= " PM";
            }
            if (hour==0) {
                hour = 12;
            }

            // Get leading zero
            String min = String.format("%02d",calendar.get(Calendar.MINUTE));

            actTime.setText(hour + ":" + min + state);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
