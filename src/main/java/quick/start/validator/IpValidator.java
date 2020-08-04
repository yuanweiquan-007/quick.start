package quick.start.validator;

import java.util.regex.Pattern;

public class IpValidator extends Validator {

     public static final Pattern REGEX_IP_ADDR = Pattern.compile("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)");

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.IP.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_IP_ADDR);
     }
}
