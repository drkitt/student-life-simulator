package comp3350.studentlifesimulator.tests.integration;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.tests.business.TestDatabaseManager;

public class TestBusinessPersistenceSeam extends TestCase {
    TestDatabaseManager testDatabaseManager;
    DatabaseAccess db;

    public TestBusinessPersistenceSeam(String arg0) {
        super(arg0);

        initializeDB();
    }

    public void testStudentAccess() {
    }

    public void testCoursesAccess() {
        initializeDB();

        testDatabaseManager.testStandardCourseSelection();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
    }

    public void testTimeAccess() {
    }

    public void testActionAccess() {
    }

    public void testSavingRoutine() {
    }

    private void initializeDB() {
        testDatabaseManager = new TestDatabaseManager(null);

        DatabaseServices.openDatabaseAccess(Main.getDBName());
        db = (DatabaseAccess)DatabaseServices.getDatabaseAccess();
        testDatabaseManager.setDB(db);
    }
}
