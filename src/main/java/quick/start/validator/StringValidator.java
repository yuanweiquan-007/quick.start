package quick.start.validator;

public class StringValidator extends Validator {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.STRING.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isString(value);
     }

}
