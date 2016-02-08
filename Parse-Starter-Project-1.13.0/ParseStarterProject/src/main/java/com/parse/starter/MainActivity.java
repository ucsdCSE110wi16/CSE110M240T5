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

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ui.ParseLoginBuilder;
import com.parse.ParseUser;

/**
 * TODO
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

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        // currUser = ParseUser.getCurrentUser();
        currUser = ParseUser.getCurrentUser();
        TextView loginText = (TextView)findViewById(R.id.loginTextView);
        if (currUser == null) {
            loginText.setText(loggedOutMessage);
        }
        else {
            loginText.setText(emptyStr);
        }


        // Database test. TODO remove later
        /* ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */
    }

    /**
     * TODO
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
        }
    }

    /**
     * TODO
     * @param v
     */
    public void viewEventsButtonOnClick(View v) {
        Intent intent = new Intent(this, EventScrollingActivity.class);
        startActivity(intent);
    }

    /**
     * TODO
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
     * TODO
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
}
