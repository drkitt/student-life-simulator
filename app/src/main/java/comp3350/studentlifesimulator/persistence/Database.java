package comp3350.studentlifesimulator.persistence;

public class Database {
    private class DataRow {
        public String[] stringData;
        public int[] intData;
        public Object[] objectData;
        public int size;

        public DataRow(int type) {
            if (type == STRING) {
                stringData = new String[MAX_DATA_SIZE];
            }
            else if (type == INTEGER) {
                intData = new int[MAX_DATA_SIZE];
            }
            else if (type == OBJECT) {
                objectData = new Object[MAX_DATA_SIZE];
            }

            size = 0;
        }
    }

    private final int MAX_DATA_SIZE = 100;
    private final int STRING = 0;
    private final int INTEGER = 1;
    private final int OBJECT = 2;

    private DataRow[] table;

    public int classesKey;
    public int studentKey;
    public int energyKey;

    public Database() {
        table = new DataRow[3];
        table[STRING] = new DataRow(STRING);
        table[INTEGER] = new DataRow(INTEGER);
        table[OBJECT] = new DataRow(OBJECT);

        classesKey = 0; // TODO: fill with Class objects (and Student object at studentKey)
        studentKey = 4;
        energyKey = INTEGER;
    }

    public int insertString(String data) {
        int key = table[STRING].size;

        table[STRING].stringData[table[STRING].size] = data;
        table[STRING].size++;

        return key;
    }

    public int insertInt(int data) {
        int key = table[INTEGER].size;

        table[INTEGER].intData[table[INTEGER].size] = data;
        table[INTEGER].size++;

        return key;
    }

    public int insertObject(Object data) {
        int key = table[OBJECT].size;

        table[OBJECT].objectData[table[OBJECT].size] = data;
        table[OBJECT].size++;

        return key;
    }

    public String getString(int key) {
        return table[STRING].stringData[key];
    }

    public int getInt(int key) {
        return table[INTEGER].intData[key];
    }

    public Object getObject(int key) {
        return table[OBJECT].objectData[key];
    }
}
