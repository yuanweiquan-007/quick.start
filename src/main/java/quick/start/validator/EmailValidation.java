package quick.start.validator;

import java.util.regex.Pattern;

public class EmailValidation extends Validation {

     public static final Pattern REGEX_EMAIL = Pattern.compile("^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.EMAIL.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return match(value, REGEX_EMAIL);
     }

     @Override
     public String validationMessage(String key) {
          return key + "邮箱格式错误";
     }
}
