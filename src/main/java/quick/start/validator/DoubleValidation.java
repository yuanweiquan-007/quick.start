package quick.start.validator;

public class DoubleValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Double.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.DOUBLE.equals(type);
     }
}
