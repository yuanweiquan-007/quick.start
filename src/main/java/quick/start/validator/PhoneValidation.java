package quick.start.validator;

import java.util.regex.Pattern;

/**
 * @author yuanweiquan
 */
public class PhoneValidation extends AbstractValidation {

     public static final Pattern REGEX_TELEPHONE = Pattern.compile("^[0][0-9]{2,3}-[0-9]{7,8}$");

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.PHONE.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_TELEPHONE);
     }

     @Override
     public String validationMessage(String key) {
          return key + "手机格式错误";
     }
}
