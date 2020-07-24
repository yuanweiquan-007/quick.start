package quick.start.util;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.start.annotation.Column;
import quick.start.annotation.Generated;
import quick.start.annotation.SaveAble;
import quick.start.entity.Entity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class BeanUtils {

     /**
      * 获取两个对象中属性值不一样的字段
      *
      * @param oldObject
      * @param newObject
      * @return
      */
     public static Map<String, Object> differents(Object oldObject, Object newObject) {
          Map<String, Object> differents = new HashMap<>();
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
                         if ((field.isAnnotationPresent(SaveAble.class)
                                 && field.getAnnotation(SaveAble.class).value() == false)
                                 || field.isAnnotationPresent(Generated.class)) {
                              continue;
                         }
                         if ((ObjectUtils.isEmpty(oldFieldValues) && !ObjectUtils.isEmpty(newFieldValues))
                                 || (!ObjectUtils.isEmpty(oldFieldValues) && ObjectUtils.isEmpty(newFieldValues))) {
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

     public static Map<String, Object> parserAttributeValues(Object object) {
          Map<String, Object> map = new HashMap<>();
          if (!ObjectUtils.isEmpty(object)) {
               Field[] fields = object.getClass().getDeclaredFields();
               for (Field field : fields) {
                    try {
                         field.setAccessible(true);
                         map.put(field.getName(), field.get(object));
                    } catch (Exception ex) {
                         ex.printStackTrace();
                    }
               }
          }
          return map;
     }

}
