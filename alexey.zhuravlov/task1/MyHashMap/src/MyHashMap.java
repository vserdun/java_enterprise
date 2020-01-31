import java.util.Objects;

public class MyHashMap {
    static int size = 16;
    private MyHashNode[] table;

    public MyHashMap() {
        table = new MyHashNode[size];
    }

    public MyHashNode[] getTable() {
        return table;
    }

    public void put(String key, String value) {
        int hash = calculateHash(key);
        int index = calcucateIndex(hash);

        if (table[index] == null) {
            table[index] = new MyHashNode(hash, key, value, null);
        } else {

            MyHashNode currentNode = table[index];
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            currentNode.setNext(new MyHashNode(hash, key, value, null));
        }
    }


    public String get(String key) {
        int hash = calculateHash(key);
        int index = calcucateIndex(hash);

        MyHashNode currentNode = table[index];

        if (currentNode == null) {
            return null;
        } else {
            while (currentNode.getHash() != hash) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                } else return null;
            }

            while (!currentNode.getKey().equals(key)) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                } else return null;
            }

            return currentNode.getValue();
        }

    }

    public void hash(MyHashNode myHashNode) {
        int hash = calculateHash(myHashNode.getKey());
        myHashNode.setHash(hash);
    }

    private int calcucateIndex(int hash) {
        return hash % (size - 1);
    }

    private int calculateHash(String key) {
        return Objects.hash(key);
    }

}

