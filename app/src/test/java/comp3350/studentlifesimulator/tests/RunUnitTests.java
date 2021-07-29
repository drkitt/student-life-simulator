package comp3350.studentlifesimulator.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.studentlifesimulator.tests.business.TestDatabaseManager;
import comp3350.studentlifesimulator.tests.business.TestStateManager;
import comp3350.studentlifesimulator.tests.business.TestStudentPerformingActions;
import comp3350.studentlifesimulator.tests.business.TestTimeFormatter;
import comp3350.studentlifesimulator.tests.objects.TestAction;
import comp3350.studentlifesimulator.tests.objects.TestCourses;
import comp3350.studentlifesimulator.tests.objects.TestEnergyBar;
import comp3350.studentlifesimulator.tests.objects.TestStudent;
import comp3350.studentlifesimulator.tests.objects.TestTime;
import comp3350.studentlifesimulator.tests.persistence.TestDatabase;

public class RunUnitTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();

        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(TestAction.class);
        suite.addTestSuite(TestCourses.class);
        suite.addTestSuite(TestEnergyBar.class);
        suite.addTestSuite(TestStudent.class);
        suite.addTestSuite(TestTime.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(TestDatabaseManager.class);
        suite.addTestSuite(TestStateManager.class);
        suite.addTestSuite(TestStudentPerformingActions.class);
        suite.addTestSuite(TestTimeFormatter.class);
    }

    private static void testPersistence() {
        suite.addTestSuite(TestDatabase.class);
    }
}
