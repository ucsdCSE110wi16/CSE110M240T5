package com.parse.starter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

/*
        // Place holder for back end to implement
        TextView myTV = (TextView) findViewById(R.id.tvEventName);
        myTV.setText("Hotdog Eating Contest");

        TextView myTV2 = (TextView) findViewById(R.id.tvEventName2);
        myTV2.setText("February 5, 2016");

        TextView myTV3 = (TextView) findViewById(R.id.tvEventName3);
        myTV3.setText("Falling Star");

        TextView myTV4 = (TextView) findViewById(R.id.tvEventName4);
        myTV4.setText("Come on down!");
*/
    }

}
