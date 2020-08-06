package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class DoubleValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Double.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.DOUBLE.equals(type);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Double类型";
     }
}
