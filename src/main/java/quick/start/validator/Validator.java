package quick.start.validator;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import quick.start.entity.Entity;
import quick.start.util.Resolver;
import quick.start.util.StringUtils;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * 验证器
 *
 * @author yuanweiquan
 */
public class Validator {

     /**
      * 验证是否成功
      */
     protected Boolean isSuccess = true;
     /**
      * 是否已经执行了验证操作
      */
     protected Boolean doValidation = false;
     /**
      * 当前验证的信息
      */
     protected ValidateMeta currentValidateElement;
     /**
      * 数据
      */
     protected Map<String, Object> data = new LinkedHashMap<>();
     /**
      * 错误信息
      */
     protected Set<String> errorMessage = new HashSet<>();
     /**
      * 所有的待验证信息
      */
     protected List<ValidateMeta> allValidateElements = new ArrayList<>();

     private static final List<AbstractValidation> VALIDATIONS = new ArrayList<>();

     static {
          for (AbstractValidation validation : ServiceLoader.load(AbstractValidation.class)) {
               VALIDATIONS.add(validation);
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

     public static Validator ofXml(String xml) throws Exception {
          return of(Resolver.ofXml(xml));
     }

     public static Validator ofJson(String json) throws Exception {
          return of(Resolver.ofJson(json));
     }

     public static Validator of(Map<String, ?> data) {
          return of(Resolver.of(data));
     }

     public static <E extends Entity> Validator of(E entity) {
          return of(Resolver.ofEntity(entity));
     }

     public ValidatorBaseType set(String key) {
          addValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(Resolver.of(data).get(key));
          return new ValidatorBaseType(this);
     }

     public ValidatorBaseType set(String key, Object value) {
          data.put(key, value);
          addValidateElementIfNecessary();
          currentValidateElement = new ValidateMeta();
          currentValidateElement.setKey(key);
          currentValidateElement.setValue(value);
          data.put(key, value);
          return new ValidatorBaseType(this);
     }

     protected void addValidateElementIfNecessary() {
          if (!ObjectUtils.isEmpty(currentValidateElement)) {
               ValidateMeta newValidateMeta = new ValidateMeta();
               BeanUtils.copyProperties(currentValidateElement, newValidateMeta);
               allValidateElements.add(newValidateMeta);
          }
     }

     protected void validate() {
          if (!doValidation && !CollectionUtils.isEmpty(allValidateElements)) {
               doValidation = true;
               for (ValidateMeta meta : allValidateElements) {
                    if (isValidate(meta)) {
                         validateMeta(meta);
                    }
               }
          }
     }

     public void ifPresent(String key, BiConsumer<String, Object> consumer) {
          Optional<Object> optional = Optional.ofNullable(Resolver.of(data).get(key));
          if (optional.isPresent()) {
               consumer.accept(key, optional.get());
          }
     }

     private void validateMeta(ValidateMeta meta) {
          for (ValidateType validateType : meta.getValidateType()) {
               AbstractValidation validation = getSupportValidation(validateType);
               if (ObjectUtils.isEmpty(validation)) {
                    errorMessage.add("不支持的验证格式:" + validateType);
                    isSuccess = false;
                    break;
               }
               if (!validation.validate(meta.getValue())) {
                    errorMessage.add(validation.validationMessage(StringUtils.isEmpty(meta.getAlias()) ? meta.getKey() : meta.getAlias()));
                    isSuccess = false;
                    break;
               }
          }
     }

     private AbstractValidation getSupportValidation(ValidateType type) {
          for (AbstractValidation validation : VALIDATIONS) {
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

     public Set<String> getErrorMessage() {
          return errorMessage;
     }


}
