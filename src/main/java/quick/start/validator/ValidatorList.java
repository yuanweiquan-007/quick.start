package quick.start.validator;

import org.springframework.util.CollectionUtils;
import quick.start.util.ArrayUtils;
import quick.start.util.Resolver;
import quick.start.util.StreamUtils;
import quick.start.util.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ValidatorList extends ValidatorBaseType {

     protected ValidatorList(Validator validator) {
          super(validator);
     }

     public ValidatorList forEach(Consumer<Validator> consumer) {
          List<Map<String, Object>> list = (List<Map<String, Object>>) validator.currentValidateElement.getValue();
          for (Map<String, Object> map : list) {
               Validator childValidator = Validator.of(map);
               consumer.accept(childValidator);
               setAliaxName(childValidator);
               if (!childValidator.isValidate()) {
                    appendErrorMessage(map, childValidator);
               }
          }
          return this;
     }

     private void setAliaxName(Validator childValidator) {
          if (!CollectionUtils.isEmpty(childValidator.allValidateElements)) {
               for (ValidateMeta meta : childValidator.allValidateElements) {
                    meta.setAlias(childKey(meta.getKey()));
               }
          }
     }

     /**
      * 将错误信息追加到父级验证器中
      *
      * @param map
      * @param childValidator
      */
     private void appendErrorMessage(Map<String, Object> map, Validator childValidator) {
          if (!CollectionUtils.isEmpty(childValidator.errorMessage)) {
               validator.isSuccess = childValidator.isSuccess;
               validator.errorMessage.addAll(childValidator.errorMessage);
          }
     }

     /**
      * 生成key
      *
      * @param key
      * @return
      */
     private String childKey(String key) {
          List<String> list = StreamUtils
                  .of(StringUtils.tokenizeToStringArray(validator.currentValidateElement.getKey(), Resolver.delimiters))
                  .collect(Collectors.toList());
          list.add(key);
          return ArrayUtils.join(".", list);
     }

}
