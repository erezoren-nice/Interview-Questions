package interview.questions.backend.lru;

import java.util.HashMap;
import java.util.Map;
import lombok.val;

public class LruCache<K, V> implements Cache<K, V> {

  int maxSize;
  Map<K, CacheNode> cache;
  CacheNode<K, V> head;
  CacheNode<K, V> tail;

  LruCache(int maxSize) {
    this.maxSize = maxSize;
    cache = new HashMap<>();
    head = new CacheNode<>(null, null);
    tail = new CacheNode<>(null, null);

    head.setNext(tail);
    tail.setPrev(head);
  }

  @Override
  public V get(K key) {
    CacheNode cacheNode = cache.get(key);
    if (cacheNode == null) {
      return null;
    }
    V value = (V) cacheNode.getValue();
    removeNode(cacheNode);
    addToHead(cacheNode);
    return value;
  }

  @Override
  public void put(K key, V value) {

    val cacheNode = cache.get(key);
    if (cacheNode != null) {
      cacheNode.setValue(value);
      removeNode(cacheNode);
      addToHead(cacheNode);
    } else {
      if (cache.size() >= maxSize) {
        removeNode(tail.getPrev());
      }
      val newNode = new CacheNode<>(key, value);
      addToHead(newNode);
    }
  }

  @Override
  public V remove(K key) {
    V remove = (V) cache.remove(key);
    return remove;
  }

  void addToHead(CacheNode cacheNode) {
    cacheNode.setNext(head.getNext());
    head.getNext().setPrev(cacheNode);
    head.setNext(cacheNode);
    cacheNode.setPrev(head);

    cache.put((K) cacheNode.getKey(), cacheNode);
  }

  void removeNode(CacheNode cacheNode) {
    cacheNode.getNext().setPrev(cacheNode.getPrev());
    cacheNode.getPrev().setNext(cacheNode.getNext());
    cache.remove(cacheNode.getKey());
  }

  public static void main(String[] args) {
    LruCache<Integer, String> lruCache = new LruCache<>(3);
    lruCache.put(1, "First");
    lruCache.put(2, "Second");
    System.out.println(lruCache.get(1));
    lruCache.put(3, "Third");
    lruCache.put(4, "Forth");
    lruCache.put(5, "Fifth");
    lruCache.put(6, "Sixth");
    System.out.println(lruCache.get(1));
    System.out.println(lruCache.get(2));
    System.out.println(lruCache.get(3));
    System.out.println(lruCache.get(4));
    System.out.println(lruCache.get(5));
    System.out.println(lruCache.get(6));
  }
}
