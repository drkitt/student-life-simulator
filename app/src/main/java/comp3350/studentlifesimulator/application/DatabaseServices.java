package comp3350.studentlifesimulator.application;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

public class DatabaseServices {
    private static DatabaseAccessInterface database = null;

    public static void openDatabaseAccess() {
        if (database == null) {
            database = new DatabaseAccess();
            database.openDB(Main.getDBPath());

            DatabaseManager.setDatabase();
        }
    }

    public static void openDatabaseAccess(DatabaseAccessInterface newDatabase) {
        database = newDatabase;
        database.openDB(Main.getDBPath());

        DatabaseManager.setDatabase();
    }

    public static void closeDatabaseAccess() {
        if (database != null) {
            database.closeDB();
            database = null;
        }
    }

    public static DatabaseAccessInterface getDatabaseAccess() {
        return database;
    }

    public static boolean checkPreviousData() {
        return DatabaseManager.getSelectedCourses().size() != 0;
    }
}
