package quick.start.validator;

public class ShortValidator extends NumberValidator {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Short.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.SHORT.equals(type);
     }
}
