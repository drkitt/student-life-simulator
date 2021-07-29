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
import static comp3350.studentlifesimulator.tests.acceptance.EspressoTestsMatchers.withDrawable;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CharacterCustomizationTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testStartGame() {
        onView(withId(R.id.newGameButton)).check(matches(isDisplayed()));
        onView(withId(R.id.newGameButton)).check(matches(isEnabled()));
        onView(withId(R.id.creditsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.creditsButton)).check(matches(isEnabled()));
        onView(withId(R.id.newGameButton)).perform(click());

        onView(withText("Customize your character")).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("Save")).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onView(withId(R.id.registerButton)).perform(click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_glasses)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair4_medium)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_fair)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_purple_featuring_whee)));
        onView(withId(R.id.deskImage)).check(matches(withDrawable(R.drawable.desk_and_monitor)));
        onView(withId(R.id.backgroundImage)).check(matches(isDisplayed()));

        Espresso.pressBack();
        Espresso.pressBack();
    }

    @Test
    public void testCustomizeCharacter() {
        onView(withId(R.id.newGameButton)).perform(click());
        onView(withId(R.id.eyesBlueButton)).perform(click());
        onView(withId(R.id.hair6Button)).perform(scrollTo(), click());
        onView(withId(R.id.skinColourDarkButton)).perform(scrollTo(), click());
        onView(withId(R.id.shirtOrangeButton)).perform(scrollTo(), click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_blue)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair6)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_dark)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_orange)));
        onView(withId(R.id.saveButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onView(withId(R.id.registerButton)).perform(click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_blue)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair6)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_dark)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_orange)));
        onView(withId(R.id.editCharacterButton)).check(matches(isDisplayed()));
        onView(withId(R.id.editCharacterButton)).check(matches(isEnabled()));
        onView(withId(R.id.editCharacterButton)).perform(click());

        onView(withId(R.id.eyesGreenButton)).perform(click());
        onView(withId(R.id.hair1LightButton)).perform(scrollTo(), click());
        onView(withId(R.id.skinColourTanButton)).perform(scrollTo(), click());
        onView(withId(R.id.shirtBlueButton)).perform(scrollTo(), click());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_green)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair1_light)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_tan)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_blue)));
        Espresso.pressBack();
        Espresso.pressBack();
    }

    @Test
    public void testLoadFromContinueGame() {
        onView(withId(R.id.newGameButton)).perform(click());
        onView(withId(R.id.eyesBlueButton)).perform(click());
        onView(withId(R.id.hair6Button)).perform(scrollTo(), click());
        onView(withId(R.id.skinColourDarkButton)).perform(scrollTo(), click());
        onView(withId(R.id.shirtOrangeButton)).perform(scrollTo(), click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_blue)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair6)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_dark)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_orange)));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withText("Select courses to be enrolled in")).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.courses)).atPosition(2).perform(click());
        onView(withId(R.id.registerButton)).perform(click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_blue)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair6)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_dark)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_orange)));
        onView(withId(R.id.editCharacterButton)).check(matches(isDisplayed()));
        onView(withId(R.id.editCharacterButton)).check(matches(isEnabled()));
        onView(withId(R.id.editCharacterButton)).perform(click());

        onView(withId(R.id.eyesGreenButton)).perform(click());
        onView(withId(R.id.hair1LightButton)).perform(scrollTo(), click());
        onView(withId(R.id.skinColourTanButton)).perform(scrollTo(), click());
        onView(withId(R.id.shirtBlueButton)).perform(scrollTo(), click());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_green)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair1_light)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_tan)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_blue)));

        Espresso.pressBack();
        Espresso.pressBack();

        onView(withId(R.id.newGameButton)).check(matches(isDisplayed()));
        onView(withId(R.id.continueButton)).check(matches(isDisplayed()));
        onView(withId(R.id.continueButton)).perform(click());
        onView(withId(R.id.eyesImage)).check(matches(withDrawable(R.drawable.eyes_green)));
        onView(withId(R.id.hairImage)).check(matches(withDrawable(R.drawable.hair1_light)));
        onView(withId(R.id.skinColourImage)).check(matches(withDrawable(R.drawable.skin_tan)));
        onView(withId(R.id.shirtImage)).check(matches(withDrawable(R.drawable.shirt_blue)));

    }

}
