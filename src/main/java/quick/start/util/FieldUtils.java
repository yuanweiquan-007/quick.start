package quick.start.util;

import quick.start.annotation.Column;

import java.lang.reflect.Field;

/**
 * @author yuanweiquan
 */
public class FieldUtils {

     public static String getFieldName(Field field) {
          return AnnotationUtils.isAnnotationPresent(field, Column.class) ? field.getAnnotation(Column.class).value() : field.getName();
     }

}
