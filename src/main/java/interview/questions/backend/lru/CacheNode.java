package interview.questions.backend.lru;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheNode<K, V> {

  private K key;
  private V value;
  private CacheNode<K, V> next;
  private CacheNode<K, V> prev;

  public CacheNode(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
