package comp3350.studentlifesimulator.application;

public class Main {
    private static final String databaseName = "StudentDB";
    private static String databasePath = "db/StudentDB";

    public static void main(String[] args) {
        DatabaseServices.openDatabaseAccess();
    }

    public static String getDBPath() {
        return databasePath;
    }

    public static String getDBName() {
        return databaseName;
    }

    public static void setDBPath(String pathName) {
        databasePath = pathName;
    }

    public static void openDBAccess() {
        DatabaseServices.openDatabaseAccess();
    }

    public static void closeDBAccess() {
        DatabaseServices.closeDatabaseAccess();
    }

    public static boolean checkPreviousData() {
        return DatabaseServices.checkPreviousData();
    }
}
