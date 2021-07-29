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
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.anything;

import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TimeManagementTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testPartTimeStudent() {
        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));

        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("Pick a course!")).inRoot(
                withDecorView(not(mainActivity.getActivity().getWindow().getDecorView()))
        ).check(matches(isDisplayed()));
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(3).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("8:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Gossip")).check(matches(isDisplayed()));
        onView(withText("Listen")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Listen")).perform(click());
        onView(withText("9:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("11:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Gossip")).check(matches(isDisplayed()));
        onView(withText("Listen")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Listen")).perform(click());
        onView(withText("12:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("0:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));

        Espresso.pressBack();
        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));
    }

    @Test
    public void testFullTimeStudent() {
        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(1).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(3).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("You can only select a maximum of 4 courses!")).inRoot(
                withDecorView(not(mainActivity.getActivity().getWindow().getDecorView()))
        ).check(matches(isDisplayed()));
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        // De-selecting
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(1).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(3).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());

        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(3).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(4).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("8:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Gossip")).check(matches(isDisplayed()));
        onView(withText("Listen")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Listen")).perform(click());
        onView(withText("9:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Nap")).perform(click());
        onView(withText("10:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("11:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Skip Class")).perform(click());
        onView(withText("Attend")).check(matches(not(isDisplayed())));
        onView(withText("Skip Class")).check(matches(not(isDisplayed())));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Study")).perform(click());
        onView(withText("Study")).perform(click());
        onView(withText("7:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("7:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("Quick Study")).perform(click());
        onView(withText("10:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Gossip")).check(matches(isDisplayed()));
        onView(withText("Listen")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Gossip")).perform(click());
        onView(withText("11:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Marathon Study")).perform(click());
        onView(withText("7:00 PM\nTuesday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Study")).perform(click());
        onView(withText("You don't have enough energy!")).inRoot(
                withDecorView(not(mainActivity.getActivity().getWindow().getDecorView()))
        ).check(matches(isDisplayed()));
        onView(withText("7:00 PM\nTuesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("7:00 AM\nWednesday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("8:00 AM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Attend")).perform(click());
        onView(withText("Gossip")).check(matches(isDisplayed()));
        onView(withText("Listen")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Quick Study")).perform(click());
        onView(withText("9:00 AM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        Espresso.pressBack();
        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));
    }

    @Test
    public void testSleeperSkipperStudent() {
        onView(withId(R.id.newGameButton)).perform(click());
        onView(withText("Customize your character")).check(matches(isDisplayed()));

        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(1).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withText("6:00 AM\nMonday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));
        onView(withText("Hibernate")).check(matches(isDisplayed()));
        onView(withText("Sleep")).check(matches(isDisplayed()));
        onView(withText("Nap")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("6:00 PM\nMonday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Hibernate")).perform(click());
        onView(withText("6:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Study")).perform(click());
        onView(withText("10:00 AM\nTuesday\nWeek 1")).check(matches(isDisplayed()));

        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("Nap")).perform(click());
        onView(withText("8:00 AM\nWednesday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Attend")).check(matches(isDisplayed()));
        onView(withText("Skip Class")).check(matches(isDisplayed()));

        onView(withText("Skip Class")).perform(click());
        onView(withText("Attend")).check(matches(not(isDisplayed())));
        onView(withText("Skip Class")).check(matches(not(isDisplayed())));

        onView(withText("Marathon Study")).perform(click());
        onView(withText("Marathon Study")).perform(click());
        onView(withText("0:00 AM\nThursday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(not(isDisplayed())));
        onView(withText("Study")).check(matches(not(isDisplayed())));
        onView(withText("Quick Study")).check(matches(not(isDisplayed())));

        onView(withText("Hibernate")).perform(click());
        onView(withText("12:00 PM\nThursday\nWeek 1")).check(matches(isDisplayed()));
        onView(withId(R.id.energyBar)).check(matches(isDisplayed()));
        onView(withText("Marathon Study")).check(matches(isDisplayed()));
        onView(withText("Study")).check(matches(isDisplayed()));
        onView(withText("Quick Study")).check(matches(isDisplayed()));

        Espresso.pressBack();
        Espresso.pressBack();
        onView(withText("Continue")).check(matches(isDisplayed()));
    }

}
