package quick.start.validator;

/**
 * @author yuanweiquan
 */
public class EmptyValidation extends AbstractValidation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.EMPTY.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isEmpty(value);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为空";
     }
}
