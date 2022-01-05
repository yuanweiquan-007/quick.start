package quick.start.util;

import org.springframework.util.ObjectUtils;
import quick.start.annotation.Generated;
import quick.start.annotation.SaveAble;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * bean帮助类
 */
public class BeanUtils {

     /**
      * 获取两个对象中属性值不一样的字段
      *
      * @param oldObject 老对象
      * @param newObject 新对象
      * @return 不一致的值的集合
      */
     public static Map<String, Object> differents(Object oldObject, Object newObject) {
          Map<String, Object> differents = new HashMap<>(16);
          if (!ObjectUtils.isEmpty(newObject) && !ObjectUtils.isEmpty(oldObject)) {
               Field[] fields = newObject.getClass().getDeclaredFields();
               Map<String, Object> oldValues = parserAttributeValues(oldObject);
               for (Field field : fields) {
                    try {
                         field.setAccessible(true);
                         Object newFieldValues = field.get(newObject);
                         Object oldFieldValues = oldValues.get(field.getName());
                         if (ObjectUtils.isEmpty(oldFieldValues) && ObjectUtils.isEmpty(newFieldValues)) {
                              continue;
                         }
                         if (!saveAble(field)) {
                              continue;
                         }
                         if (oneAndOnlyOneIsEmpty(newFieldValues, oldFieldValues)) {
                              differents.put(field.getName(), newFieldValues);
                         }
                         if (!ObjectUtils.nullSafeToString(oldFieldValues).equals(ObjectUtils.nullSafeToString(newFieldValues))) {
                              differents.put(field.getName(), newFieldValues);
                         }
                    } catch (Exception e) {
                         e.printStackTrace();
                    }
               }
          }
          return differents;
     }

     private static boolean oneAndOnlyOneIsEmpty(Object newFieldValues, Object oldFieldValues) {
          return (ObjectUtils.isEmpty(oldFieldValues) && !ObjectUtils.isEmpty(newFieldValues))
                  || (!ObjectUtils.isEmpty(oldFieldValues) && ObjectUtils.isEmpty(newFieldValues));
     }

     private static Boolean saveAble(Field field) {
          return !(field.isAnnotationPresent(SaveAble.class)
                  && !field.getAnnotation(SaveAble.class).value())
                  && !field.isAnnotationPresent(Generated.class);
     }

     public static Map<String, Object> parserAttributeValues(Object object) {
          Map<String, Object> map = new HashMap<>(16);
          if (!ObjectUtils.isEmpty(object)) {
               Field[] fields = object.getClass().getDeclaredFields();
               for (Field field : fields) {
                    try {
                         field.setAccessible(true);
                         map.put(FieldUtils.getFieldName(object.getClass(), field), field.get(object));
                    } catch (Exception ex) {
                         ex.printStackTrace();
                    }
               }
          }
          return map;
     }

}
