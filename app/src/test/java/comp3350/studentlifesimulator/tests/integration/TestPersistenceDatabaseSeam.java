package comp3350.studentlifesimulator.tests.integration;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.tests.persistence.TestDatabase;

public class TestPersistenceDatabaseSeam extends TestCase {
    private Statement statement;

    public TestPersistenceDatabaseSeam(String arg0) {
        super(arg0);
    }

    public void testStudentPersistence() {
        TestDatabase testDb = new TestDatabase(null);
        DatabaseAccess db;
        ResultSet results;
        Student initialStudent;

        DatabaseServices.openDatabaseAccess(Main.getDBName());
        db = (DatabaseAccess)DatabaseServices.getDatabaseAccess();
        try {
            statement = db.getConnection().createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        TestDatabase.setDb(db);
        initialStudent = db.getStudent();

        testDb.testStudent();
        try {
            results = statement.executeQuery("SELECT * FROM STUDENTS");
            assertTrue(results.next());
            assertEquals("Over 9000", results.getString("STUDENTNAME"));
            assertEquals(0, results.getInt("CURRENTENERGY"));
            assertEquals(9999, results.getInt("STUDENTSCORE"));

            statement.executeUpdate("UPDATE STUDENTS SET STUDENTNAME = '" +
                    initialStudent.getStudentName() + "', CURRENTENERGY = " +
                    initialStudent.getCurrentEnergy() + ", STUDENTSCORE = " +
                    initialStudent.getScore() + " WHERE STUDENTID = 0");
            assertEquals("Anne Otherstudent", db.getStudent().getStudentName());
            assertEquals(12, db.getStudent().getCurrentEnergy());
            assertEquals(0, db.getStudent().getScore());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseServices.closeDatabaseAccess();
    }

    public void testCoursePersistence() {
    }
}
