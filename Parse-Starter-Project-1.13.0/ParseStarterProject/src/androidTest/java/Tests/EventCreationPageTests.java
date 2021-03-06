package Tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.ParseException;
import com.parse.ParseUser;
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
 * Finished by Hans ... zzz....
 */
@RunWith(AndroidJUnit4.class)
public class EventCreationPageTests {

    private String eventTitle;
    private String eventLocation;
    private String eventCapacity;
    private String eventDescription;
    private String tags;
    private String crappyTitle, crappyLocation, crappyCapacity, crappyDesc;


    @Rule
    public ActivityTestRule<AddNewActivity> mActivityRule = new ActivityTestRule<AddNewActivity>(AddNewActivity.class);

    @Before
    public void initVariables() {
        eventTitle = "Title Test YEEEEEEE";
        eventLocation = "Location Test YEEEEEE";
        eventCapacity = "" + 10;
        eventDescription = "This quarter has been a huge amount of ups and downs but I had a lot of fun" +
                " this quarter. I had two programming courses simultaneously for the first time, and " +
                "also for the first time I am taking upper division CS courses. all in all, though, I thought" +
                "this was a lot of fun. But now that WInter Quarter is FINALLY over (right?? No more surprise" +
                " work? :D) I'm going to enjoy my spring break.... after grading a bunch of finals today............";
        tags = "welcome,to,kesden's office";

        crappyTitle = "fheofihjoifdsjiofjeioagjriolgjfislohgorishgogjhfiodrjfidsojfisjgiors;gji;ojfiaosfdsijgeraklgj";

        crappyLocation = "9450 Gilman Dr, La Jolla, CA 92092";

        crappyCapacity = "99999";

        crappyDesc = "Voluntary[edit] Sleep deprivation can sometimes be self-imposed due to " +
                "a lack of desire to sleep or the habitual use of stimulant drugs. Sleep " +
                "deprivation is also self-imposed to achieve personal fame in the context " +
                "of record-breaking stunts. Sleep apnea[edit] Sleep apnea (obstructive " +
                "sleep apnea, OSA) is a collapse of the upper airway during sleep, which";

        try {
            ParseUser.logIn("hanzzeh", "kjidfe");
        } catch (ParseException e) {}


        // Espresso.onView(ViewMatchers.withId(R.id.createNewEventButton)).perform(ViewActions.click());
        //   Espresso.onView(ViewMatchers.withId(R.id.ToCreationPage)).perform(ViewActions.click());
    }


    @Test
    public void tryToFAilThis() {
        //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).perform(ViewActions.typeText(crappyTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(crappyTitle)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        //Click the datepicker
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_activityDate)).perform(ViewActions.typeText("05/28/16"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_activityDate)).check(ViewAssertions.matches(ViewMatchers.withText("05/28/16")));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {}

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyTime)).perform(ViewActions.typeText("12:00 am"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyTime)).check(ViewAssertions.matches(ViewMatchers.withText("12:00 am")));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(crappyLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(crappyLocation)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).perform(ViewActions.typeText(tags), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).check(ViewAssertions.matches(ViewMatchers.withText(tags)));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact)).perform(ViewActions.typeText("16261234567"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact)).check(ViewAssertions.matches(ViewMatchers.withText("16261234567")));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).perform(ViewActions.typeText(crappyCapacity), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).check(ViewAssertions.matches(ViewMatchers.withText(crappyCapacity)));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeTextIntoFocusedView(crappyDesc), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(crappyDesc)));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.bt_NEWACTIVITY_submit))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }

    @Test
    public void eventTitleTest() {
        //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).perform(ViewActions.typeText(eventTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(eventTitle)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        //Click the datepicker
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.timePickah)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(eventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(eventLocation)));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).perform(ViewActions.typeText(tags), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_tags)).check(ViewAssertions.matches(ViewMatchers.withText(tags)));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact)).perform(ViewActions.typeText("16261234567"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_contact)).check(ViewAssertions.matches(ViewMatchers.withText("16261234567")));


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).perform(ViewActions.typeText(eventCapacity), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).check(ViewAssertions.matches(ViewMatchers.withText(eventCapacity)));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc))
                .perform(ViewActions.scrollTo(), ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeTextIntoFocusedView(eventDescription), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(eventDescription)));

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
        }


        Espresso.onView(ViewMatchers.withId(R.id.bt_NEWACTIVITY_submit))
                .perform(ViewActions.scrollTo(), ViewActions.click());


    }
}
