package quick.start.validator;

public class LongValidator extends NumberValidator {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Long.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.LONG.equals(type);
     }
}
