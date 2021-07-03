package comp3350.studentlifesimulator.application;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

public class DBServices {
    private static DatabaseAccessInterface database = null;

    public static boolean openDatabaseAccess(String databaseName) {
        boolean opened = false;

        if (database == null) {
            database = new DatabaseAccess(databaseName);
            database.openDB(Main.getDBPath());

            DatabaseManager.setDatabase(database);

            opened = true;
        }

        return opened;
    }

    public static DatabaseAccessInterface getDatabaseAccess() {
        return null;
    }

    public static void closeDatabaseAccess() {
        if (database != null) {
            database.closeDB();
            database = null;
        }
    }
}
