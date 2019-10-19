import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();

        hashMap.put("key", "value");
        hashMap.put("key2", "value2");
        hashMap.put("yek", "value3");
        hashMap.put("yke", "value4");
        hashMap.put("kye2", "value5");


        MyHashNode[] nodes = hashMap.getTable();
        for (int i = 0; i < nodes.length; i++) {
            System.out.println("index:" + i + " " + nodes[i]);
        }

        System.out.println(hashMap.get("kye2"));
        System.out.println(hashMap.get("key2"));

    }
}
