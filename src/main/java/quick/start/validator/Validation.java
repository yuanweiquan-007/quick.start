package quick.start.validator;

import quick.start.Support;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class Validation implements Validatable, Support<ValidateType> {

     /**
      * 判断当前值是否为空
      *
      * @param value
      * @return
      */
     public static boolean isPresent(Object value) {
          return value != null;
     }

     public boolean isEmpty(Object value) {
          if (!isPresent(value)) {
               return true;
          }
          if (value instanceof String) {
               return String.valueOf(value).trim().equals("");
          }
          if (value instanceof CharSequence) {
               return ((CharSequence) value).length() == 0;
          }
          if (value.getClass().isArray()) {
               return Array.getLength(value) == 0;
          }
          if (value instanceof Collection) {
               return ((Collection) value).isEmpty();
          }
          if (value instanceof Map) {
               return ((Map) value).isEmpty();
          }
          return false;
     }

     /**
      * 判断当前值是否与指定的正则匹配
      *
      * @param value
      * @param pattern
      * @return
      */
     public static boolean match(Object value, Pattern pattern) {
          if (!isPresent(value) || !isString(value) || pattern == null) {
               return false;
          }
          return pattern.matcher(String.valueOf(value)).matches();
     }

     /**
      * 判断当前值是否字符串
      *
      * @param value
      * @return
      */

     public static boolean isString(Object value) {
          return value instanceof String;
     }

}
