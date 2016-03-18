package Tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
//import android.support.test.espresso.action.contrib.*;
import android.support.test.espresso.*;
import android.view.View;
import android.widget.*;
import com.parse.starter.AddNewActivity;
import com.parse.starter.R;
import android.content.Context;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;
import android.support.test.espresso.*;


/**
 * Created by Ryan on 3/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class EventCreationPageTests {

    private String eventTitle;
    private String eventLocation;
    private String eventCapacity;
    private String eventDescription;
    private String tags;

    @Rule public ActivityTestRule<AddNewActivity> mActivityRule = new ActivityTestRule<AddNewActivity>(AddNewActivity.class);

    @Before
    public void initVariables() {
        eventTitle = "Title Test YEEEEEEE";
        eventLocation = "Location Test YEEEEEE";
        eventCapacity = "" + 10;
        eventDescription = " h ";
        tags = "welcome,to,kesdensoffice";

       // Espresso.onView(ViewMatchers.withId(R.id.createNewEventButton)).perform(ViewActions.click());
     //   Espresso.onView(ViewMatchers.withId(R.id.ToCreationPage)).perform(ViewActions.click());
    }


    @Test
    public void eventTitleTest() {
        //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());



        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).perform(ViewActions.typeText(eventTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(eventTitle)));

        //Click the datepicker
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.timePickah)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(eventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(eventLocation)));

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).perform(ViewActions.typeText(tags), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).check(ViewAssertions.matches(ViewMatchers.withText(tags)));

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).perform(ViewActions.typeText("15"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).check(ViewAssertions.matches(ViewMatchers.withText("15")));


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeTextIntoFocusedView(eventDescription), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(eventDescription)));

    
    }
/*
    @Test
    public void eventLocationTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(eventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(eventLocation)));
    }

    @Test
    public void eventCapacityTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeText("What is happening"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText("What is happening")));
    }

    @Test
    public void eventDescriptionTest() {
        //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(eventDescription)));
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeTextIntoFocusedView(eventDescription), ViewActions.closeSoftKeyboard());
    }
*/
    /*@After
    public void afterTest(){
        Espresso.pressBack();
    }*/

    //@Test


}
