package quick.start.validator;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 验证信息
 */
@Data
public class ValidateMeta {

     private String key;
     private Object value;
     private Boolean required = true;
     private Set<ValidateType> validateType = new HashSet<>();
     private boolean isValidated;
     private String message;

     public void addValidateType(ValidateType validateType) {
          this.validateType.add(validateType);
     }

}
