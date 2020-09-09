package quick.start.util;

import quick.start.annotation.Column;
import quick.start.annotation.MapUnderScoreToCamelCase;

import java.lang.reflect.Field;

/**
 * @author yuanweiquan
 */
public class FieldUtils {

     /**
      * 获取字段名字
      * 优先级@Column注解大于@MapUnderScoreToCamelCase
      *
      * @param classs 类
      * @param field  字段
      * @return 字段名称
      */
     public static String getFieldName(Class<?> classs, Field field) {
          if (AnnotationUtils.isAnnotationPresent(field, Column.class)) {
               return field.getAnnotation(Column.class).value();
          }
          if (AnnotationUtils.isAnnotationPresent(classs, MapUnderScoreToCamelCase.class)) {
               return StringUtils.humpToLine(field.getName());
          }
          return field.getName();
     }

}
