package Tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.starter.AddNewActivity;
import com.parse.starter.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

//import android.support.test.espresso.action.contrib.*;


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
        eventDescription = "I may or may not be thinking about all nighters right now particularly for this quarter.";
        tags = "welcome,to,kesdensoffice";

       // Espresso.onView(ViewMatchers.withId(R.id.createNewEventButton)).perform(ViewActions.click());
     //   Espresso.onView(ViewMatchers.withId(R.id.ToCreationPage)).perform(ViewActions.click());
    }


    @Test
    public void eventTitleTest() {
        //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());



        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).perform(ViewActions.typeText(eventTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(eventTitle)));



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}

        //Click the datepicker
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}

        Espresso.onView(ViewMatchers.withId(R.id.timePickah)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(eventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(eventLocation)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}



        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).perform(ViewActions.typeText(tags), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).check(ViewAssertions.matches(ViewMatchers.withText(tags)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).perform(ViewActions.typeText("15"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).check(ViewAssertions.matches(ViewMatchers.withText("15")));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e){}


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeTextIntoFocusedView(eventDescription), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(eventDescription)));


    }

    @Test
    public void tagsTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).perform(ViewActions.typeText(tags), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).check(ViewAssertions.matches(ViewMatchers.withText(tags)));
    }
/*
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
