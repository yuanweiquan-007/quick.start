package quick.start.validator;

public class NotEmptyValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.NOT_EMPTY.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return !isEmpty(value);
     }

     @Override
     public String validationMessage(String key) {
          return key + "不能为空";
     }

}
