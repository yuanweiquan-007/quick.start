package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class LongValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Long.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.LONG.equals(type);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Long类型";
     }
}
