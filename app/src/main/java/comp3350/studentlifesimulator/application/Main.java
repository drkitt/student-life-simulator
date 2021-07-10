package comp3350.studentlifesimulator.application;

public class Main {
    private static String databaseName = "StudentDB";
    private static String databasePath = "db/StudentDB";

    public static void main(String[] args) {
        DatabaseServices.openDatabaseAccess(databaseName);
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
        DatabaseServices.openDatabaseAccess(databaseName);
    }

    public static boolean checkPreviousData() {
        return DatabaseServices.checkPreviousData();
    }
}
