package quick.start.validator;

public class IntegerValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Integer.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.INTEGER.equals(type);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Int类型";
     }
}
