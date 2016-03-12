package Tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.parse.starter.AddNewActivity;
import com.parse.starter.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Ryan on 3/11/2016.
 */
@RunWith(AndroidJUnit4.class)
public class EventCreationPageTests {

    private String eventTitle;
    private String eventLocation;
    private String eventCapacity;
    private String eventDescription;

    @Rule public final ActivityTestRule<AddNewActivity> mActivityRule = new ActivityTestRule<AddNewActivity>(AddNewActivity.class);

    @Before
    public void initVariables() {
        eventTitle = "Test";
        eventLocation = "Test Location";
        eventCapacity = "10";
        eventDescription = "This is a test to see that the event creation page is working";

        Espresso.onView(ViewMatchers.withId(R.id.createNewEventButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.ToCreationPage)).perform(ViewActions.click());
    }

    @Test
    public void eventTitleTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).perform(ViewActions.typeText(eventTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(eventTitle)));
    }

    @Test
    public void eventLocationTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).perform(ViewActions.typeText(eventLocation), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyLoc)).check(ViewAssertions.matches(ViewMatchers.withText(eventLocation)));
    }

    @Test
    public void eventCapacityTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).perform(ViewActions.typeText(eventCapacity), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_capacity)).check(ViewAssertions.matches(ViewMatchers.withText(eventCapacity)));
    }

    @Test
    public void eventDescriptionTest() {
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).perform(ViewActions.typeText(eventDescription), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_desc)).check(ViewAssertions.matches(ViewMatchers.withText(eventDescription)));
    }
}
