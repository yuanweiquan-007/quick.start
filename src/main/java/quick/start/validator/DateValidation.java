package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class DateValidation extends AbstractValidation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.DATE.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isDate(value, "yyyy-MM-dd HH:mm:ss");
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为时间类型(yyyy-MM-dd HH:mm:ss)";
     }

}
