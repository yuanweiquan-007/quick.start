package quick.start;

import lombok.Data;

/**
 * @author yuanweiquan
 */
@Data
public class KeyValues<K extends Object, V extends Object> {

     /**
      * key
      */
     protected K key;
     /**
      * value
      */
     protected V value;

     /**
      * 默认构造
      * @param key key
      * @param value value
      */
     public KeyValues(K key, V value) {
          this.key = key;
          this.value = value;
     }

}
