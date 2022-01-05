package quick.start.util;

import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author yuanweiquan
 */
public class AnnotationUtils {

     /**
      * 得到注解属性
      *
      * @param annotation 注解
      * @param function   函数
      * @return {@link T}
      */
     public static <T, A extends Annotation> T getAnnotationAttribute(A annotation, Function<A, T> function) {
          return function.apply(annotation);
     }

     /**
      * 是否包含注解
      *
      * @param field           字段
      * @param annotationClass 注解类
      * @return {@link Boolean}
      */
     public static Boolean isAnnotationPresent(Field field, Class<? extends Annotation> annotationClass) {
          return !ObjectUtils.isEmpty(field) && field.isAnnotationPresent(annotationClass);
     }

     /**
      * 是否包含注解
      *
      * @param field           字段
      * @param annotationClass 注解类
      * @param function        函数
      * @return {@link Boolean}
      */
     public static <A extends Annotation> Boolean isAnnotationPresent(Field field, Class<A> annotationClass, Function<A, Boolean> function) {
          return isAnnotationPresent(field, annotationClass) && function.apply(field.getAnnotation(annotationClass));
     }

     /**
      * 是否包含注解
      *
      * @param classs          、
      * @param annotationClass 注解类
      * @return {@link Boolean}
      */
     public static Boolean isAnnotationPresent(Class<?> classs, Class<? extends Annotation> annotationClass) {
          return !ObjectUtils.isEmpty(classs) && classs.isAnnotationPresent(annotationClass);
     }

     public static <A extends Annotation> void isAnnotationPresent(Class<?> classs, Class<A> annotationClass, Consumer<A> consumer) {
          if (isAnnotationPresent(classs, annotationClass)) {
               consumer.accept(classs.getAnnotation(annotationClass));
          }
     }

}
