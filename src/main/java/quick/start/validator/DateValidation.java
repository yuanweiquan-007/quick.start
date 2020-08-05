package quick.start.validator;

import java.util.Date;

public class DateValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.DATE.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return Date.class.isAssignableFrom(value.getClass());
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为时间类型(yyyy-MM-dd HH:mm:ss)";
     }

}
