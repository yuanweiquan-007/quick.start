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
               if (!childValidator.isValidate()) {
                    appendErrorMessage(map, childValidator);
               }
          }
          return this;
     }

     /**
      * 将错误信息追加到父级验证器中
      *
      * @param map
      * @param childValidator
      */
     private void appendErrorMessage(Map<String, Object> map, Validator childValidator) {
          if (!CollectionUtils.isEmpty(childValidator.errorMessage)) {
               validator.isSuccess = false;
               childValidator.errorMessage.forEach(message -> {
                    validator.errorMessage.add(replaceKey(map, message));
               });
          }
     }

     /**
      * 替换错误信息提示的key
      *
      * @param data
      * @param message
      * @return
      */
     private String replaceKey(Map<String, Object> data, String message) {
          for (String key : data.keySet()) {
               if (message.contains(key)) {
                    message = message.replace(key, childKey(key));
               }
          }
          return message;
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
