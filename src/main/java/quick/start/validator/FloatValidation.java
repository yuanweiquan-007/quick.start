package quick.start.validator;

public class FloatValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Float.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.FLOAT.equals(type);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Float类型";
     }
}
