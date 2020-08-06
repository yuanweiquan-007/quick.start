package quick.start.util;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author yuanweiquan
 */
public class ArrayUtils {

     /**
      * 将集合拼接为字符串
      *
      * @param delimiter
      * @param original
      * @return
      */
     public static <T> String join(final String delimiter, final Collection<T> original) {
          if (original == null || original.isEmpty()) {
               return "";
          }

          StringJoiner joiner = new StringJoiner(delimiter);
          original.forEach(x -> joiner.add(String.valueOf(x)));
          return joiner.toString();
     }

}
