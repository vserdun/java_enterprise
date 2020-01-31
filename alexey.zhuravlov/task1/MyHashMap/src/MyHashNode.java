import java.util.Objects;

public class MyHashNode {
    private int hash;
    private String key;
    private String value;
    private MyHashNode next;

    public MyHashNode() {
    }

    public MyHashNode(int hash, String key, String value, MyHashNode next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MyHashNode getNext() {
        return next;
    }

    public void setNext(MyHashNode next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyHashNode that = (MyHashNode) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "MyHashNode{" +
                "hash=" + hash +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", next=" + next +
                '}';
    }
}
