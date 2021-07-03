package comp3350.studentlifesimulator.application;

import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

public class Services {
    private static DatabaseAccessInterface database = null;

    public static boolean openDatabaseAccess(String databaseName) {
        boolean opened = false;

        if (database == null) {
            database = new DatabaseAccess(databaseName);
            database.openDB(Main.getPath());

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
