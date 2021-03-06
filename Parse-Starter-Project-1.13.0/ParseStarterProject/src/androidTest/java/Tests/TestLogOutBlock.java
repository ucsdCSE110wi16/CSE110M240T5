package Tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.parse.ParseUser;
import com.parse.starter.MainActivity;
import com.parse.starter.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hans and Ryan Melissa Alex on 3/11/2016.
 *  Hans Managed to get it working with correct build version the allnighter after.
 */
@RunWith(AndroidJUnit4.class)
public class TestLogOutBlock {

    private String username;
    private String password;
    private String email;
    private String name;
    private String tags;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void initVariables() {
        Random random = new Random();
        for (int i = random.nextInt(7); i < 9; i++) {

            ParseUser.logOut();
            username += random.nextInt(26);
            password += random.nextInt(9);
            email += random.nextInt(11);
            name += random.nextInt(7);
            tags = "welcome,to,kesdensoffice";
        }

        // Espresso.onView(ViewMatchers.withId(R.id.createNewEventButton)).perform(ViewActions.click());
        //   Espresso.onView(ViewMatchers.withId(R.id.ToCreationPage)).perform(ViewActions.click());
    }

    @After
    public void LogOut() {
        ParseUser.logOut();
    }
    @Test
    public void dummyTest(){}

    @Test
    public void eventLoginTest() {

        //LOGIN
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.login_username_input)).perform(ViewActions.typeText("hanzzeh"), ViewActions.closeSoftKeyboard());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.login_password_input)).perform(ViewActions.typeText("kjidfe"), ViewActions.closeSoftKeyboard());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withText("Log in")).perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        //press Log out

        Espresso.onView(ViewMatchers.withId(R.id.signInOutButton))
                .perform(ViewActions.click());

        //Press Back
        Espresso.pressBack();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

        Espresso.onView(ViewMatchers.withId(R.id.viewEventsButton))
                .perform(ViewActions.click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }

    }









    //Espresso.onView(ViewMatchers.withId(R.id.here)).perform(ViewActions.swipeDown());

    //Sign out
    //Espresso.onView(ViewMatchers.withId(R.id.replyButton)).perform(ViewActions.click());
    //Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click());
        /*
        Espresso.onView(ViewMatchers.withId(R.id.signInOutButton)).perform(ViewActions.typeText(eventTitle), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.et_NEWACTIVITY_actvitiyName)).check(ViewAssertions.matches(ViewMatchers.withText(eventTitle)));

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
*/

}

