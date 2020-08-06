package quick.start.validator;

import java.util.regex.Pattern;

/**
 * @author yuanweiquan
 */
public class UrlValidation extends AbstractValidation {

     public static final Pattern REGEX_URL = Pattern.compile("^(http|www|ftp)://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$", Pattern.CASE_INSENSITIVE);

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.URL.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_URL);
     }

     @Override
     public String validationMessage(String key) {
          return key + "错误的URL格式";
     }

}
