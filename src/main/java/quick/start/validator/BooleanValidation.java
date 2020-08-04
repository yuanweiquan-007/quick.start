package quick.start.validator;

public class BooleanValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.BOOLEAN.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          if (value == null) {
               return false;
          }
          if (value instanceof Boolean) {
               return true;
          }
          if (value instanceof Number) {
               return value.equals(1) || value.equals(0) || value.equals(1L) || value.equals(0L);
          }
          return false;
     }

}
