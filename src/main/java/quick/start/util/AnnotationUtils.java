package quick.start.util;

import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Function;

public final class AnnotationUtils {

     public static <T, A extends Annotation> T getAnnotationAttribute(A annotation, Function<A, T> function) {
          return function.apply(annotation);
     }

     public static Boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotationClass) {
          return !ObjectUtils.isEmpty(field) && field.isAnnotationPresent(annotationClass);
     }

     public static <A extends Annotation> Boolean isAnnotationPresent(Field field, Class<A> annotationClass, Function<A, Boolean> function) {
          return isAnnotationPresent(field, annotationClass) && function.apply(field.getAnnotation(annotationClass));
     }

     public static Boolean isAnnotationPresent(Class<?> classs, Class<? extends Annotation> annotationClass) {
          return !ObjectUtils.isEmpty(classs) && classs.isAnnotationPresent(annotationClass);
     }

     public static <A extends Annotation> void annotationOption(Class<?> classs, Class<A> annotationClass, Consumer<A> consumer) {
          if (isAnnotationPresent(classs, annotationClass)) {
               consumer.accept(classs.getAnnotation(annotationClass));
          }
     }

}
