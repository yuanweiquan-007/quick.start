package quick.start.validator;

import java.util.Collection;

public class CollectionValidation extends Validation {

     @Override
     public boolean isSupported(ValidateType type) {
          return ValidateType.COLLECTION.equals(type);
     }

     @Override
     public boolean validate(Object value) {
          return isClass(value, Collection.class);
     }

     @Override
     public String validationMessage(String key) {
          return key + "必须为集合类型";
     }

}
