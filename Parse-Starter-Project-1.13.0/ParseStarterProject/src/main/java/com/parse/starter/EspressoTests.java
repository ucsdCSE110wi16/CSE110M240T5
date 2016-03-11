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
 * Created by M. Ly on 3/10/2016.
 */

public class EspressoTests {
    @RunWith(AndroidJUnit4.class)
    @Before
    public Event defaultEvent = new Event();

    @Test
    public void signIn() {
        onView(withId(R.id.signInOutButton)).perform(click());
        //onView(withId(R.id.display_text)).check(matches(withText(goodbye)));
    }

    @Test
    public void typeEventName() {
        onView(withID(R.id.et_NEWACTIVITY_actvitiyName)).perform(typeText("NEW ACTIVITY"),
                closeSoftKeyboard());
        onView(withId(R.id.et_NEWACTIVITY_actvitiyName)).check(matches(withText("NEW ACTIVITY")));
    }

    @Test
    public void textNotCutOff() {
        onView(withID(R.id.et_NEWACTIVITY_actvitiyName)).perform(typeText("SUPER DUPER REALLY UBER UNBELIEVABLY STUPIDLY RIDICULOUSLY LONG AS MY DIIIIIINNER NEW ACTIVITY"),
                closeSoftKeyboard());
        onView(withId(R.id.et_NEWACTIVITY_actvitiyName)).check(matches(noEllipsizedText()));
    }


}
