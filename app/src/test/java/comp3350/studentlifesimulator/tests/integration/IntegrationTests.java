package comp3350.studentlifesimulator.tests.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests {
    public static TestSuite integrationSuite;

    public static Test suite() {
        integrationSuite = new TestSuite("Integration Tests");

        integrationSuite.addTestSuite(TestBusinessPersistenceSeam.class);
        integrationSuite.addTestSuite(TestPersistenceDatabaseSeam.class);

        return integrationSuite;
    }
}
