package quick.start.validator;

public class ByteValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Byte.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.BYTE.equals(type);
     }
}
