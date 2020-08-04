package quick.start.validator;

public class EmptyValidator extends Validator {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.EMPTY.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isEmpty(value);
     }
}
