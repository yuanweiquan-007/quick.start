package quick.start.validator;

import java.util.Collection;

/**
 * @author yuanweiquan
 */
public class CollectionValidation extends AbstractValidation {

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
