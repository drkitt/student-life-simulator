package comp3350.studentlifesimulator.tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import comp3350.studentlifesimulator.tests.acceptance.CharacterCustomizationTest;
import comp3350.studentlifesimulator.tests.acceptance.TimeManagementTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({TimeManagementTest.class, CharacterCustomizationTest.class})
public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Acceptance Tests");
    }
}
