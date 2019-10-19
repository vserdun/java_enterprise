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
        int hash = Objects.hash(key);
        int index = hash % (size - 1);

        if (table[index] == null) {
            table[index] = new MyHashNode(hash, key, value, null);
        } else {
            MyHashNode currentNode = table[index];
            while (table[index].getNext() != null) {
                table[index] = table[index].getNext();
            }

            table[index].setNext(new MyHashNode(hash, key, value, null));
        }
    }

    public String get(String key) {
        int hash = Objects.hash(key);
        int index = hash % (size - 1);

        MyHashNode currentNode = table[index];

        if (currentNode == null) {
            return null;
        }

        else {
            while (currentNode.getHash() != hash) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                }
                else return null;
            }

            while (!currentNode.getKey().equals(key)) {
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                }
                else return null;
            }

            return currentNode.getValue();
        }

    }

    public void hash(MyHashNode myHashNode) {
        int hash = Objects.hash(myHashNode.getKey());
        myHashNode.setHash(hash);
    }

}

