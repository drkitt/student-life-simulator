package comp3350.studentlifesimulator.persistence;

public class DatabaseManager {
    private class DataNode {
        private DataNode next;
        private Database data;

        public DataNode() {
            next = null;
            data = new Database();
        }
    }

    private DataNode head;

    public DatabaseManager() {
        head = new DataNode();
    }

    public int createDatabase() {
        int databaseID = 0;

        return databaseID;
    }

    public Database getData(int id) {
        return head.data;
    }

    public boolean addData(int id, String data) {
        boolean added = true;

        return added;
    }
}
