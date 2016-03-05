/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ui.ParseLoginBuilder;
import com.parse.ParseUser;

/**
 * Hans 2/22/16
 * Changed the login screen to appear first if the user is not signed
 * in. If the user is signed in, though, it will go straight to the
 * Event Rows.
 */
public class MainActivity extends ActionBarActivity {

    private ParseUser currUser;

    private static final String loggedOutMessage = "You are logged out.";
    private static final String emptyStr = "";

    /**
     * TODO
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView loginText = (TextView)findViewById(R.id.loginTextView);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //ParseUser.logOutInBackground();
        // currUser = ParseUser.getCurrentUser();
        currUser = ParseUser.getCurrentUser();

        if(currUser == null) {
            //Enter the Login Screen
            Toast.makeText(getApplicationContext(),
                    "Welcome. Please log in.", Toast.LENGTH_LONG).show();

            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);


        }

        else {
            //Access event rows
            Toast.makeText(getApplicationContext(),
                    "Welcome. GET UPURVENTTTT", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);

        }

/*
        if (currUser == null) {
            loginText.setText(loggedOutMessage);
        }
        else {
            loginText.setText(emptyStr);
        }
        */
    }

    /**
     * Handles logging in and logging out user as a ParseUser
     * @param v
     */
    public void signInOutButtonOnClick(View v) {
        TextView loginText = (TextView)findViewById(R.id.loginTextView);
        if (currUser == null) {
            // Open login builder and update currUser
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
            currUser = ParseUser.getCurrentUser();
            if (currUser != null) { // Sign in succeeded
                loginText.setText(emptyStr);
            }
        }
        else {
            ParseUser.logOut();
            currUser = null;
            loginText.setText(loggedOutMessage);
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
        }
    }

    /**
     * Starts EventsActivity
     * @param v
     */
    public void viewEventsButtonOnClick(View v) {
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    /**
     * Initialize the AddNewActivity screen
     * @param v
     */
    public void createNewEventButtonOnClick(View v){

        Intent intent = new Intent( this, AddNewActivity.class);
        startActivity( intent );
    }


    /**
     * TODO Add options to the options menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * TODO do something with settings or remove
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ParseUser.logOutInBackground();
    }
}
