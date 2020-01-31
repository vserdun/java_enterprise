package customMap;

public class CustomHashMap<K,V> implements CustomMap<K,V> {
    private static final int DEFAULT_CAPACITY = 16;

    private int capacity;

    private Node<K,V>[] nodes;

    public CustomHashMap(int capacity){
        nodes = (Node<K,V>[])new Node[capacity];
        this.capacity = capacity;
    }

    public CustomHashMap(){
        this(DEFAULT_CAPACITY);
    }

    private int hash(Object key){
        int h;
        return (key == null) ? 0 : key.hashCode();
    }

    private int indexFor(Object key){
        return hash(key) & (capacity - 1);
    }

    private boolean checkKeyCoincidence(Node<K,V> node, K key) {
        return (key == null) ? (node.getHash() == (hash(key))) : (node.getHash() == (hash(key))) && (key.equals(node.getKey()));
    }

    @Override
    public V put(K key, V value){
        int index = indexFor(key);
        Node<K,V> input = new Node<>(hash(key), key, value, null);
        Node<K,V> e = nodes[index];

        if((e == null)){
            nodes[index] = input;
        } else if(checkKeyCoincidence(e, key)) {
            input.setNext(e.next);
            nodes[index] = input;
            return e.value;
        }else{
            if(e.next == null){
                e.next = input;
            }
            else{
                Node<K,V> current = e.next;
                Node<K,V> next = current.next;
                do{
                    if(checkKeyCoincidence(current,key)){
                        input.setNext(next);
                        e.next = input;
                        return current.value;
                    }else if(next == null){
                        current.next = input;
                        return current.value;
                    }else{
                        e = current;
                        current = next;
                        next = next.next;
                    }
                }while (true);
            }
        }
        return null;
    }

    @Override
    public V get(K key){
        int index = indexFor(key);
        Node<K,V> e = nodes[index];

        while (e != null){
            if(checkKeyCoincidence(e, key)) return e.value;
            e = e.next;
        }

        return null;
    }

    static class Node<K,V>{
        final int hash;
        final K key;
        V value;

        Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "k = " + key +
                    ", v = " + value +
                    '}';
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public int getHash() {
            return hash;
        }
    }
}
