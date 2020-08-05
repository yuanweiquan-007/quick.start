package quick.start.validator;

import java.util.Map;

public class MapValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.MAP.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isClass(value, Map.class);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为Map类型";
     }

}
