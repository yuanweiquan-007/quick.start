package quick.start.validator;

import java.util.regex.Pattern;

public class PhoneValidator extends Validator {

     public static final Pattern REGEX_TELEPHONE = Pattern.compile("^[0][0-9]{2,3}-[0-9]{7,8}$");

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.PHONE.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_TELEPHONE);
     }
}
