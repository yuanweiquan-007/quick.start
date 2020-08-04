package quick.start.validator;

import org.springframework.util.NumberUtils;

public class NumberValidator extends Validator {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Number.class);
     }

     /**
      * 判断当前值是否指定类型的number
      * @param value
      * @param clazz
      * @param <T>
      * @return
      */
     public static <T extends Number> boolean isNumber(Object value, Class<T> clazz) {
          try {
               T number = NumberUtils.parseNumber(String.valueOf(value), clazz);
               return true;
          } catch (IllegalArgumentException e) {
               return false;
          }
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.NUMBER.equals(type);
     }
}
