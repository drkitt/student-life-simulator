package comp3350.studentlifesimulator.application;

public class Main {
    private static String databaseName = "SC";
    private static String databasePath = "db/SC";

    public static void main(String[] args) {
        Services.openDatabaseAccess(databaseName);
    }

    public static String getPath() {
        return databasePath;
    }
}
