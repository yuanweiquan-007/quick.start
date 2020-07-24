package quick.start.util;

import org.springframework.util.ObjectUtils;
import quick.start.annotation.Column;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Function;

public final class FieldUtils {

     public static String getFieldName(Field field) {
          return isAnnotationPresent(field, Column.class) ? field.getAnnotation(Column.class).value() : field.getName();
     }

     public static Boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotationClass) {
          return !ObjectUtils.isEmpty(field) && field.isAnnotationPresent(annotationClass);
     }

     public static <A extends Annotation> Boolean isAnnotationPresent(Field field, Class<A> annotationClass, Function<A, Boolean> function) {
          return isAnnotationPresent(field, annotationClass) && function.apply(field.getAnnotation(annotationClass));
     }

}
