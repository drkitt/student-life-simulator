package comp3350.studentlifesimulator.tests.acceptance;

import androidx.test.espresso.Espresso;
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
import static org.hamcrest.Matchers.anything;

import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameProgressionScoreTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testContinueGameScore() {
        onView(withId(R.id.newGameButton)).check(matches(isDisplayed()));
        onView(withId(R.id.newGameButton)).check(matches(isEnabled()));
        onView(withId(R.id.creditsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.creditsButton)).check(matches(isEnabled()));

        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));
        onView(withText("Save")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());

        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 0")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Sleep")).perform(click());
        onView(withText("Sleep")).perform(click());
        onView(withText("7:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 37")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("Hibernate")).perform(click());
        onView(withText("Hibernate")).perform(click());
        onView(withText("Marathon Study")).perform(click());
        onView(withText("3:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 97")).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));

        onView(withText("Continue")).perform(click());
        onView(withText("3:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 97")).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Quick Study")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("4:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 97")).check(matches(isDisplayed()));

        onView(withText("Nap")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("8:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 111")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));

        onView(withText("Continue")).perform(click());
        onView(withText("8:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 111")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));
    }

    @Test
    public void testNewGameScore() {
        onView(withId(R.id.newGameButton)).check(matches(isDisplayed()));
        onView(withId(R.id.newGameButton)).check(matches(isEnabled()));
        onView(withId(R.id.creditsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.creditsButton)).check(matches(isEnabled()));

        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));
        onView(withText("Save")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());

        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 0")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Sleep")).perform(click());
        onView(withText("Sleep")).perform(click());
        onView(withText("7:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 37")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("Hibernate")).perform(click());
        onView(withText("Hibernate")).perform(click());
        onView(withText("Marathon Study")).perform(click());
        onView(withText("3:00 PM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 97")).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));

        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));
        onView(withText("Save")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());

        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 0")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));

        onView(withText("Sleep")).perform(click());
        onView(withText("Sleep")).perform(click());
        onView(withText("2:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withText("Score: 12")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));

        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));
        onView(withText("Save")).check(matches(isDisplayed()));

        Espresso.pressBack();
        onView(withText("Continue")).check(matches(not(isDisplayed())));
    }
}
