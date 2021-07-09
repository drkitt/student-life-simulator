package comp3350.studentlifesimulator.application;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

public class DatabaseServices {
    private static DatabaseAccessInterface database = null;

    public static boolean openDatabaseAccess(String databaseName) {
        boolean opened = false;

        if (database == null) {
            database = new DatabaseAccess(databaseName);
            database.openDB(Main.getDBPath());

            DatabaseManager.setDatabase();

            opened = true;
        }

        return opened;
    }

    public static boolean openDatabaseAccess(DatabaseAccessInterface newDatabase) {
        boolean opened = false;

        if (database == null) {
            database = newDatabase;
            database.openDB(Main.getDBPath());

            DatabaseManager.setDatabase();

            opened = true;
        }

        return opened;
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
}
