public class MyHashMap<E, V> {

    private final int DEFAULT_SIZE = 16;

    private Entry[] entries = new Entry[DEFAULT_SIZE];
    private int size = 0;

    public void put(E key, V value) {
        int pos = getPos(key.hashCode());
        Entry<E, V> e = entries[pos];
        if (e == null) {
            putEntry(pos, new Entry(key.hashCode(), key, value, null));
        } else {
            if (!e.findEntry(key.hashCode(), key, value)) {
                putEntry(pos, new Entry(key.hashCode(), key, value, e));
            }
        }
    }

    private int getPos(int h){
        return h & (DEFAULT_SIZE - 1);
    }

    private void putEntry(int pos, Entry<E, V> entry) {
        entries[pos] = entry;
        size++;
    }

    public V get(E key) {
        int pos = getPos(key.hashCode());
        Entry<E, V> entry = entries[pos];
        if (entry != null) {
            return entry.findValue(key);
        } else {
            return null;
        }
    }

    public int size() {
        return size;
    }

    public Entry<E, V>[] getEntries() {
        return entries;
    }

    public static class Entry<E, V> {

        int hash;
        E key;
        V value;
        Entry next;

        public Entry(int hash, E key, V value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        boolean findEntry(int hash, E key, V value) {
            if (next != null) {
                if (this.hash == hash || (this.key == key && this.key.equals(key))) {
                    this.value = value;
                    return true;
                } else {
                    return next.findEntry(hash, key, value);
                }
            } else {
                return false;
            }
        }

        V findValue(E key) {
            if (this.key == key && this.key.equals(key)) {
                return value;
            } else {
                if (next != null) {
                    return (V) next.findValue(key);
                } else {
                    return null;
                }
            }
        }
    }
}
