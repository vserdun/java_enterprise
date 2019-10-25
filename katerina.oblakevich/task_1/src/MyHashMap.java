public class MyHashMap {

    private MapEntry[] entries;
    private int capacity = 16;

    public MyHashMap() {
        entries = new MapEntry[capacity];
    }

    public class MapEntry {
        Integer key;
        String value;
        MapEntry next;

        public MapEntry(Integer key, String value, MapEntry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Integer keyNew, String value) {
        int hash = hash(keyNew);
        MapEntry newEntry = new MapEntry(keyNew, value, null);
        if (entries[hash] == null) {
            entries[hash] = newEntry;
        } else {
            MapEntry previous = null;
            MapEntry current = entries[hash];

            while (current != null) {
                if (current.key.equals(keyNew)) {
                    if (previous == null) {
                        newEntry.next = current.next;
                        entries[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public String get (Integer key) {
        int hash = hash(key);
        if (entries[hash] == null) {
            return null;
        } else {
            MapEntry temp = entries[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp.value;
                temp = temp.next;
            }
            return null;
        }
    }

    public boolean remove (Integer keyForDelete) {
        int hash = hash(keyForDelete);

        if (entries[hash] == null) {
            return false;
        } else {
            MapEntry previous = null;
            MapEntry current = entries[hash];

            while (current != null) {
                if (current.key.equals(keyForDelete)) {
                    if (previous == null) {
                        entries[hash] = entries[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }

    private int hash(Integer key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void toStringMap() {
        for (int i = 0; i < capacity; i++){
            if (entries[i] != null) {
                MapEntry entry = entries[i];
                while (entry != null) {
                    System.out.println("{" + entry.key + "=" + entry.value + "}" + " ");
                    entry = entry.next;
                }
            }
        }
    }

}
