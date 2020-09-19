package quick.start.collection;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author yuanweiquan
 */
@Getter
public class Pair<K, V> implements Serializable {

    private final K key;

    private final V value;

    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair of(K key, V value) {
        Pair<Object, Object> pair = new Pair<>(key, value);
        return pair;
    }
}
