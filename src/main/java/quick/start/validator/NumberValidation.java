package quick.start.validator;

import org.springframework.util.NumberUtils;

/**
 * @author yuanweiquan
 */
public class NumberValidation extends AbstractValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Number.class);
     }

     /**
      * 判断当前值是否指定类型的number
      *
      * @param value 对象
      * @param clazz 类型
      * @param <T>   泛型参数
      * @return 是否是指定类型
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

     @Override
     public String validationMessage(String key) {
          return key + "必须为数字类型";
     }
}
