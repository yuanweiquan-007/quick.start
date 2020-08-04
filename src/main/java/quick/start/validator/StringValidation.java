package quick.start.validator;

public class StringValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.STRING.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isString(value);
     }

}
