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

        public Entry(K key, V value) {
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
        }else { //if there is smth in the bucket, add our entry at the end
            Entry<K, V> previous = null;
            while(currentEntry != null) {
                if (currentEntry.key.equals(key)) { //overwrite entry if the same key exists
                    entryToBeAdded.next = currentEntry.next;
                    previous.next = entryToBeAdded;
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
        return null;
    }

    //returns location of the bucket
    public int findBucket(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

}
