package quick.start.validator;

public class EmptyValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.EMPTY.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isEmpty(value);
     }
}
