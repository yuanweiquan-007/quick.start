package quick.start.validator;

import lombok.Data;

import java.util.Set;

/**
 * 验证信息
 */
@Data
public class ValidateMeta {
     private String key;
     private Object value;
     private Set<ValidateType> validateType;
     private boolean isValidated;
     private String message;
}
