package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class NotEmptyValidation extends AbstractValidation {

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
