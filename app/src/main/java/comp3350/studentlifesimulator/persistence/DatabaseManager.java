package comp3350.studentlifesimulator.persistence;

public class DatabaseManager {
    private static Database database = new Database();

    public DatabaseManager() {

    }

    // BASE METHODS FOR GETTING INFO AT START

    public String[] getClasses() {
        String[] classes = null;

        return classes;
    }

    public Object getStudent() { // TODO: Change so that it returns Student object
        Object student = null;

        return student;
    }

    public int getMaxEnergy() {
        int maxEnergy = -1;

        return maxEnergy;
    }

    public int insertString(String data) {
        int key = -1;

        return key;
    }

    public int insertInt(int data) {
        int key = -1;

        return key;
    }

    public int insertObject(Object data) {
        int key = -1;

        return key;
    }

    public String getString(int key) {
        String data = null;

        return data;
    }

    public int getInt(int key) {
        int data = -1;

        return data;
    }

    public Object getObject(int key) {
        Object data = null;

        return data;
    }
}
