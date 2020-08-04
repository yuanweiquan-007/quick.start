package quick.start.validator;

import java.util.regex.Pattern;

public class MobileValidator extends Validator {

     public static final Pattern REGEX_MOBILE = Pattern.compile("^1\\d{10}$");

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.MOBILE.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_MOBILE);
     }
}
