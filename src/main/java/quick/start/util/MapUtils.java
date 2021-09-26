package quick.start.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiuge
 * @date 2021/9/26 7:52 下午
 * @Version 1.0.0
 */
public class MapUtils {

    public static <K, V> Map<K, V> toMap(K key, V value) {
        HashMap<K, V> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
