package quick.start.validator;

public class NotEmptyValidator extends Validator {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.NOT_EMPTY.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return !isEmpty(value);
     }

}
