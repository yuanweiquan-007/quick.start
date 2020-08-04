package quick.start.validator;

public class FloatValidator extends NumberValidator {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Float.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.FLOAT.equals(type);
     }
}
