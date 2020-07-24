package quick.start.util;

import java.lang.annotation.Annotation;
import java.util.function.Function;

public final class AnnotationUtils {

     public static <T> T getAnnotationAttribute(Annotation annotation, Function<Annotation, T> function) {
          return function.apply(annotation);
     }

}
