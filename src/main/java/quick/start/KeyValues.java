package quick.start;

import lombok.Data;

/**
 * @author yuanweiquan
 */
@Data
public class KeyValues<K extends Object, V extends Object> {

     protected K key;
     protected V value;

     public KeyValues(K key, V value) {
          this.key = key;
          this.value = value;
     }

}
