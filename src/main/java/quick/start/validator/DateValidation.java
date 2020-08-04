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

}
