package com.parse.starter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addNewActivity extends AppCompatActivity implements View.OnClickListener
{
    Button bSubmit;
    EditText actName, actLoc, actTime, actTags;
    String dataInputCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        actName = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyName);
        actLoc = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyLoc);
        actTime = (EditText) findViewById(R.id.et_NEWACTIVITY_actvitiyTime);
        actTags = (EditText) findViewById(R.id.et_NEWACTIVITY_tags);

        bSubmit = (Button) findViewById(R.id.bt_NEWACTIVITY_submit);
        bSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        boolean validInputForm = true;
        //Check activity name
        dataInputCheck = actName.getText().toString();
        if (dataInputCheck.matches(""))
        {
            validInputForm = false;
        }
        //Check activity location
        dataInputCheck = actLoc.getText().toString();
        if (dataInputCheck.matches(""))
        {
            validInputForm = false;
        }
        //Check activity time
        dataInputCheck = actTime.getText().toString();
        if (dataInputCheck.matches(""))
        {
            validInputForm = false;
        }
        if (!validInputForm)
        {
            Toast.makeText(this,
                    "Your event is missing some information",
                    Toast.LENGTH_SHORT).show();
        }

        if(v.getId() == R.id.bt_NEWACTIVITY_submit && validInputForm)
        {
            //Send data to the parse Database.
        }
    }
}
