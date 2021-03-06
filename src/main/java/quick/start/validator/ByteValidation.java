package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class ByteValidation extends NumberValidation {

     @Override
     public boolean validate(Object value) {
          return isNumber(value, Byte.class);
     }

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.BYTE.equals(type);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Byte类型";
     }
}
