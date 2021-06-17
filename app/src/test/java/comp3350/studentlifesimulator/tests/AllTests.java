package comp3350.studentlifesimulator.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.studentlifesimulator.tests.business.TestStudentPerformingActions;
import comp3350.studentlifesimulator.tests.objects.TestAction;
import comp3350.studentlifesimulator.tests.objects.TestCourses;
import comp3350.studentlifesimulator.tests.objects.TestEnergyBar;
import comp3350.studentlifesimulator.tests.objects.TestStudent;
import comp3350.studentlifesimulator.tests.objects.TimeTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(TestAction.class);
        suite.addTestSuite(TestCourses.class);
        suite.addTestSuite(TestEnergyBar.class);
        suite.addTestSuite(TestStudent.class);
        suite.addTestSuite(TimeTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(TestStudentPerformingActions.class);
    }
}
