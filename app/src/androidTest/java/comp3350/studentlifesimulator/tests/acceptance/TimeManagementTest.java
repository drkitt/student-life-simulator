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

    @Test
    public void testPartTimeStudent() {
        onView(withId(R.id.newGameButton)).perform(click());

        // Skipping the Character customization and just continue
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
//    public void testSleeperSkipperStudent() {
//
//    }
//
}
