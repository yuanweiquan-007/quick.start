package quick.start.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiuge
 */
public class MapUtils {

    public static <K, V> Map<K, V> toMap(K key, V value) {
        HashMap<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
