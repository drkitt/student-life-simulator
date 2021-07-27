package comp3350.studentlifesimulator.tests.acceptance;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.studentlifesimulator.R;

import org.junit.*;
import org.junit.runner.RunWith;

import comp3350.studentlifesimulator.presentation.MainActivity;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

//import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TimeManagementTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    // TODO: move this method to game progression test
    @Test
    public void testStartNewGame() {
        // TODO: do we want to put the title of the app on the screen for testing?
        // onView(withText("Student Life Simulator")).check(matches(isDisplayed()));
        onView(withId(R.id.newGameButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        //onView(withText(R.id.creditsButton)).check(matches(isDisplayed())).check(matches(isEnabled()));

        // TODO: double-check button visibility
        // if INVISIBLE:
        // onView(withText("Continue")).check(matches(not(isDisplayed())));
        // if GONE/is not in the layout at all
        onView(withText("Continue")).check(doesNotExist());
    }

    @Test
    public void testPartTimeStudent() {
        onView(withId(R.id.newGameButton)).perform(click());
        // TODO: Take tags for each attribute
        // onView(withTagKey(equalTo(R.id.)))
        // onView(withTagKey(equalTo(R.id.)))
        // onView(withTagKey(equalTo(R.id.)))
        // onView(withTagKey(equalTo(R.id.)))
        onView(withText("Continue")).perform(click());
        onView(withText("Select courses")).check(matches(isDisplayed()));

        onView(withId(R.id.registerButton)).perform(click());
        // confirm toast/tooltip

        onView(withText("COMP1010")).perform(click());
        onView(withText("COMP1020")).perform(click());
        onView(withText(R.id.registerButton)).perform(click());
        onView(withText("Week 1")).check(matches(isDisplayed()));
        onView(withText("Day 1")).check(matches(isDisplayed()));
        onView(withText("6:00 AM")).check(matches(isDisplayed()));
    }

//    @Test
//    public void testFullTimeStudent() {
//
//    }
//
//    @Test
//    public void testSleeperStudent() {
//
//    }
//
//    @Test
//    public void testStudyAndSkipStudent() {
//
//    }
}
