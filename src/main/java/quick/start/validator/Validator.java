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

     private Boolean isSuccess = true;
     private ValidateMeta currentValidateElement;
     private Map<String, Object> data = new HashMap<>();
     private List<String> errorMessage = new ArrayList<>();
     private List<ValidateMeta> allValidateElements = new ArrayList<>();

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
          allValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(Resolver.of(data).get(key));
          return new ValidatorSet(this);
     }

     public ValidatorSet set(String key, Object value) {
          allValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(value);
          return new ValidatorSet(this);
     }

     private void allValidateElementIfNecessary() {
          if (!ObjectUtils.isEmpty(currentValidateElement)) {
               ValidateMeta newValidateMeta = new ValidateMeta();
               BeanUtils.copyProperties(currentValidateElement, newValidateMeta);
               allValidateElements.add(newValidateMeta);
          }
     }

     public void validate() {
          if (!CollectionUtils.isEmpty(allValidateElements)) {
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
               }
               if (!validation.validate(meta.getValue())) {
                    errorMessage.add(validation.validationMessage(meta.getKey()));
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
          return isSuccess;
     }

     public String firstErrorMessage() {
          return errorMessage.isEmpty() ? CommonConstant.NULL : errorMessage.get(0);
     }

     public List<String> getErrorMessage() {
          return errorMessage;
     }

     class ValidatorSet {

          private Validator validator;

          protected ValidatorSet(Validator validator) {
               validator = validator;
          }

          public ValidatorSet required() {
               this.validator.currentValidateElement.setRequired(true);
               return this;
          }

          public ValidatorSet sometimes() {
               this.validator.currentValidateElement.setRequired(false);
               return this;
          }

          public ValidatorSet bytes() {
               return addValidateType(ValidateType.BYTE);
          }

          public ValidatorSet bigInteger() {
               return addValidateType(ValidateType.BIGINTEGER);
          }

          public ValidatorSet bool() {
               return addValidateType(ValidateType.BOOLEAN);
          }

          public ValidatorSet collection() {
               return addValidateType(ValidateType.COLLECTION);
          }

          public ValidatorSet date() {
               return addValidateType(ValidateType.DATE);
          }

          public ValidatorSet doubles() {
               return addValidateType(ValidateType.DOUBLE);
          }

          public ValidatorSet email() {
               return addValidateType(ValidateType.EMAIL);
          }

          public ValidatorSet empty() {
               return addValidateType(ValidateType.EMPTY);
          }

          public ValidatorSet floats() {
               return addValidateType(ValidateType.FLOAT);
          }

          public ValidatorSet integer() {
               return addValidateType(ValidateType.INTEGER);
          }

          public ValidatorSet ip() {
               return addValidateType(ValidateType.IP);
          }

          public ValidatorSet longs() {
               return addValidateType(ValidateType.LONG);
          }

          public ValidatorSet map() {
               return addValidateType(ValidateType.MAP);
          }

          public ValidatorSet mobile() {
               return addValidateType(ValidateType.MOBILE);
          }

          public ValidatorSet notEmpty() {
               return addValidateType(ValidateType.NOT_EMPTY);
          }

          public ValidatorSet number() {
               return addValidateType(ValidateType.NUMBER);
          }

          public ValidatorSet phone() {
               return addValidateType(ValidateType.PHONE);
          }

          public ValidatorSet shorts() {
               return addValidateType(ValidateType.SHORT);
          }

          public ValidatorSet string() {
               return addValidateType(ValidateType.STRING);
          }

          public ValidatorSet url() {
               return addValidateType(ValidateType.URL);
          }

          private ValidatorSet addValidateType(ValidateType type) {
               this.validator.currentValidateElement.addValidateType(type);
               return this;
          }

          public ValidatorSet set(String key) {
               return validator.set(key);
          }

          public ValidatorSet set(String key, String value) {
               return validator.set(key, value);
          }

     }

}
