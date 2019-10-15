import java.util.Objects;

public class MyHashMap
{
  private MyHashNode[] table;
  int size;

  public void put(String key, String value)
  {
    int hash = Objects.hash(key);
    int index = hash % (size - 1);

    MyHashNode currentEmptyNode = table[index];

    while (currentEmptyNode != null)
    {
      currentEmptyNode = currentEmptyNode.getNext();
    }
    currentEmptyNode.setHash(hash);
    currentEmptyNode.setKey(key);
    currentEmptyNode.setValue(value);
    currentEmptyNode.setNext(null);

  }

  public String get(String key)
  {
    int hash = Objects.hash(key);
    int index = hash % (size - 1);

    MyHashNode currentNode = table[index];

    while (currentNode.getHash() != hash)
    {
      currentNode = currentNode.getNext();
    }

    while (!currentNode.getKey().equals(key))
    {
      currentNode = currentNode.getNext();
    }
    return currentNode.getValue();

  }

  public void hash(MyHashNode myHashNode)
  {
    int hash = Objects.hash(myHashNode.getKey());
    myHashNode.setHash(hash);
  }

}

