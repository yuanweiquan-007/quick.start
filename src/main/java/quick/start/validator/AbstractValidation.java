package quick.start.validator;

import quick.start.Support;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author yuanweiquan
 */
public abstract class AbstractValidation implements Validatable, Support<ValidateType> {

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
               return "".equals(String.valueOf(value).trim());
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

     /**
      * 判断当前值是否指定的对象
      *
      * @param value
      * @param clazz
      * @return
      */
     public static boolean isClass(Object value, Class<?> clazz) {
          if (value == null || clazz == null) {
               return false;
          }
          return clazz.isInstance(value);
     }

     /**
      * 判断当前值是不是一个与指定格式相符的日期时间
      *
      * @param value
      * @param format
      * @return
      */
     public static boolean isDate(Object value, String format) {
          if (!isPresent(value)) {
               return false;
          }
          if (Date.class.isAssignableFrom(value.getClass())) {
               return true;
          }

          try {
               SimpleDateFormat formatter = new SimpleDateFormat(format);
               formatter.setLenient(true);
               formatter.parse(String.valueOf(value));
               return true;
          } catch (ParseException e) {
               return false;
          }
     }

     /**
      * 传入一个key，返回验证信息
      *
      * @param key
      * @return
      */
     public abstract String validationMessage(String key);

}
