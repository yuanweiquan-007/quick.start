package quick.start.validator;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.constant.CommonConstant;
import quick.start.util.Resolver;
import quick.start.util.StringUtils;

import java.util.*;

/**
 * 验证器
 */
public class Validator {
     //验证是否成功
     protected Boolean isSuccess = true;
     //是否已经执行了验证操作
     protected Boolean doValidation = false;
     //当前验证的信息
     protected ValidateMeta currentValidateElement;
     //数据
     protected Map<String, Object> data = new HashMap<>();
     //错误信息
     protected List<String> errorMessage = new ArrayList<>();
     //所有的待验证信息
     protected List<ValidateMeta> allValidateElements = new ArrayList<>();

     private static final List<Validation> validations = new ArrayList<>();

     static {
          for (Validation validation : ServiceLoader.load(Validation.class)) {
               validations.add(validation);
          }
     }

     private Validator() {
     }

     public static Validator of() {
          return new Validator();
     }

     public static Validator of(Resolver resolver) {
          Validator validator = new Validator();
          if (!ObjectUtils.isEmpty(resolver) && !CollectionUtils.isEmpty(resolver.getData())) {
               validator.data.putAll(resolver.getData());
          }
          return validator;
     }

     public ValidatorSet set(String key) {
          addValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(Resolver.of(data).get(key));
          return new ValidatorSet(this);
     }

     public ValidatorSet set(String key, Object value) {
          addValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(value);
          return new ValidatorSet(this);
     }

     protected void addValidateElementIfNecessary() {
          if (!ObjectUtils.isEmpty(currentValidateElement)) {
               ValidateMeta newValidateMeta = new ValidateMeta();
               BeanUtils.copyProperties(currentValidateElement, newValidateMeta);
               allValidateElements.add(newValidateMeta);
          }
     }

     public void validate() {
          if (!doValidation && !CollectionUtils.isEmpty(allValidateElements)) {
               doValidation = true;
               for (ValidateMeta meta : allValidateElements) {
                    if (isValidate(meta)) validateMeta(meta);
               }
          }
     }

     private void validateMeta(ValidateMeta meta) {
          for (ValidateType validateType : meta.getValidateType()) {
               Validation validation = getSupportValidation(validateType);
               if (ObjectUtils.isEmpty(validation)) {
                    errorMessage.add("不支持的验证格式:" + validateType);
                    isSuccess = false;
               }
               if (!validation.validate(meta.getValue())) {
                    errorMessage.add(validation.validationMessage(meta.getKey()));
                    isSuccess = false;
               }
          }
     }

     private Validation getSupportValidation(ValidateType type) {
          for (Validation validation : validations) {
               if (validation.isSupported(type)) {
                    return validation;
               }
          }
          return null;
     }

     private Boolean isValidate(ValidateMeta validateMeta) {
          return !ObjectUtils.isEmpty(validateMeta)
                  && !StringUtils.isEmpty(validateMeta.getKey())
                  && !ObjectUtils.isEmpty(validateMeta.getValidateType())
                  &&
                  (
                          validateMeta.getRequired() || (!validateMeta.getRequired() && !ObjectUtils.isEmpty(validateMeta.getValue()))
                  );
     }

     public Boolean isValidate() {
          if (!doValidation) {
               validate();
          }
          return isSuccess;
     }

     public String firstErrorMessage() {
          return errorMessage.isEmpty() ? CommonConstant.NULL : errorMessage.get(0);
     }

     public List<String> getErrorMessage() {
          return errorMessage;
     }


}
