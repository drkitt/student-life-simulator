package comp3350.studentlifesimulator.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RunIntegrationTests {
    public static TestSuite integrationSuite;

    public static Test suite() {
        integrationSuite = new TestSuite("Integration Tests");

        integrationSuite.addTestSuite(TestPersistenceDatabaseSeam.class);
        integrationSuite.addTestSuite(TestBusinessPersistenceSeam.class);

        return integrationSuite;
    }
}
