public class Main1 {

    public static void main(String[] args) {

        MyHashMap myHashMap = new MyHashMap();

        myHashMap.put(2, "a");
        myHashMap.put(3, "b");
        myHashMap.put(1, "c");

        System.out.println(myHashMap.get(3));
        System.out.println(myHashMap.get(22));

        System.out.println("List:");
        myHashMap.toStringMap();


        System.out.println("If deleted with key 2: " + myHashMap.remove(2));
        System.out.println("If deleted with key 4: " + myHashMap.remove(4));

        System.out.println("List:");
        myHashMap.toStringMap();
    }
}
