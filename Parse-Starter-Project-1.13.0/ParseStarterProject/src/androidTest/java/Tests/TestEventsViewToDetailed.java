package Tests;

/**
 * Created by vectFluxUltimate on 3/18/2016.
 */

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.EventsActivity;
import com.parse.starter.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)



public class TestEventsViewToDetailed {
    private String username;
    private String password;
    private String email;
    private String name;
    private String tags;
    MyViewAction act;


    @Rule
    public ActivityTestRule<EventsActivity> mActivityRule = new ActivityTestRule<EventsActivity>(EventsActivity.class);

    @Before
    public void initVariables() {
        act = new MyViewAction();
        try {
            ParseUser.logIn("hanzzeh", "kjidfe");
        } catch (ParseException e) {}
    }

    @Test
    public void TryClick() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {}


       // RecyclerViewActions.actionOnItem()
         /*
        RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click());
        RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click());
        RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click());
        RecyclerViewActions.actionOnItemAtPosition(3, ViewActions.click());
        RecyclerViewActions.actionOnItemAtPosition(4, ViewActions.click());*/
        Espresso.onData(ViewMatchers.withId(R.id.replyButton))
                .inAdapterView(ViewMatchers.withId(R.id.here))
                .atPosition(0)
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.replyButton)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, act.clickChildViewWithId(R.id.replyButton)));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {}


   //     RecyclerViewActions.
    }

    public class MyViewAction {

        public ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    if (v != null) {
                        v.performClick();
                    }
                }
            };
        }

    }
    }

