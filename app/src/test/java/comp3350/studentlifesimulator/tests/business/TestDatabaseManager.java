package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

public class TestDatabaseManager extends TestCase {
    public TestDatabaseManager(String arg0) { super(arg0); }

    public void testDatabaseInitialization() {
        // TODO: do testing to ensure that DatabaseManager throws errors without DB

        StubDatabase stubDB = new StubDatabase();
        stubDB.openDB(null);
        DatabaseManager.setDatabase(stubDB);

        // TODO: do testing to ensure that we are not throwing errors for methods

        DatabaseAccess hsqlDB = new DatabaseAccess(Main.getDBName());
        hsqlDB.openDB(Main.getDBPath());
        DatabaseManager.setDatabase(hsqlDB);

        // TODO: do testing to ensure that we are not throwing errors for methods
    }

    public void testDataInjections() {
    }

    public void testDataQueries() {
    }
}
