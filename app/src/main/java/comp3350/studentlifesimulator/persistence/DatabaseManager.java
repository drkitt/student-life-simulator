package comp3350.studentlifesimulator.persistence;

public class DatabaseManager {
    private class DataNode {
        public DataNode next;
        public String[] data;
        public int size;

        public DataNode() {
            next = null;
            data = new String[10];
            size = 0;
        }
    }

    private DataNode head;
    private int size;

    public DatabaseManager() {
        head = new DataNode();
        size = 0;
    }

    public int createDatabase() {
        int databaseID = size + 1;
        DataNode database;

        database = getNode(size);
        database.next = new DataNode();
        size++;

        return databaseID;
    }

    public String[] getData(int id) {
        String[] targetData = {};
        DataNode target;

        if (id != 0) {
            target = getNode(id);
            targetData = target.data;
        }

        return targetData;
    }

    public boolean addData(int id, String data) {
        boolean added = false;
        DataNode target;

        if (id != 0) {
            target = getNode(id);

            if (target != null) {
                insert(target, data);
                added = true;
            }
        }

        return added;
    }

    private DataNode getNode(int id) {
        DataNode target = null;

        if (id <= size) {
            target = head;

            for (int i = 0; i < id; i++) {
                target = target.next;
            }
        }

        return target;
    }

    private void insert(DataNode node, String data) {
        String[] temp;

        if (node.size >= node.data.length) {
            temp = node.data;
            node.data = new String[temp.length + 10];

            for (int i = 0; i < node.data.length; i++) {
                node.data[i] = temp[i];
            }
        }

        node.data[node.size] = data;
        node.size++;
    }
}
