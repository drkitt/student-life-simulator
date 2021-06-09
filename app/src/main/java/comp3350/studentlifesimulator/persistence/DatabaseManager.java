package comp3350.studentlifesimulator.persistence;

public class DatabaseManager {
    private final static Database DATABASE = new Database();

    public DatabaseManager() {
    }

    public Object[] getClasses() { // TODO: Change so that it returns list of Class object
        Object[] classes = new Object[DATABASE.studentKey - DATABASE.classesKey];

        for (int i = DATABASE.classesKey; i < DATABASE.studentKey; i++) {
            classes[i - DATABASE.classesKey] = DATABASE.getObject(i);
        }

        return classes;
    }

    public Object getStudent() { // TODO: Change so that it returns Student object
        return DATABASE.getObject(DATABASE.studentKey);
    }

    public int getMaxEnergy() {
        return DATABASE.getInt(DATABASE.energyKey);
    }

    public int insertString(String data) {
        return DATABASE.insertString(data);
    }

    public int insertInt(int data) {
        return DATABASE.insertInt(data);
    }

    public int insertObject(Object data) {
        return DATABASE.insertObject(data);
    }

    public String getString(int key) {
        return DATABASE.getString(key);
    }

    public int getInt(int key) {
        return DATABASE.getInt(key);
    }

    public Object getObject(int key) {
        return DATABASE.getObject(key);
    }
}
