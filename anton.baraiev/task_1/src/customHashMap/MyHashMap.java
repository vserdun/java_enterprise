package customHashMap;

public class MyHashMap<K, V> {
    //consider size = 16 per task
    private static final int SIZE = 16;
    private Entry<K, V>[] buckets = new Entry[SIZE];

    //create a "Node" class
    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //associates the specified value with the specified key in this map:
    //  - if the map previously contained a mapping for the key, the old
    //  - value is replaced.
    public void put(K key, V value) {
        Entry<K, V> entryToBeAdded = new Entry<>(key, value);
        int entryBucket = findBucket(key);
        Entry<K, V> currentEntry = buckets[entryBucket];
        //check if the bucket isEmpty
        if (currentEntry == null) {
            buckets[entryBucket] = entryToBeAdded;
        }else { // If bucket is not empty, add your entry at the end.
            Entry<K, V> previous = null;
            while (currentEntry != null) {
                if (currentEntry.key.equals(key)) { //overwrite if there is an entry w/ such key
                    entryToBeAdded.next = currentEntry.next;
                    if(previous == null) {
                        currentEntry = entryToBeAdded;
                    } else {
                        previous.next = entryToBeAdded;  //overwrite
                    }
                    return;
                }
                previous = currentEntry;
                currentEntry = currentEntry.next;
            }
            previous.next = entryToBeAdded;
        }
    }


    //returns the value for a specified key or null if there is not such key
    public V get(K key) {
        int targetBucket = findBucket(key);
        if (buckets[targetBucket] == null) {
            return null;
        } else {
            Entry<K, V> entry = buckets[targetBucket];
            while (entry != null) {
                if (entry.key.equals(key)) { //find the matching Entry by the key
                    return entry.value;
                }
                entry = entry.next;
            }
            return null; //returns null if key is not found
        }
    }

    //returns location of the bucket
    public int findBucket(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    //some simple tests
    public static void main(String[] args) {

        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1, "a");
        myHashMap.put(2, "b");
        myHashMap.put(3, "c");

        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(2));
        System.out.println(myHashMap.get(3));
    }

}
