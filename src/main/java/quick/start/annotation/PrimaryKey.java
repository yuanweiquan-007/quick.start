package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 用来表示主键
 * @author yuanweiquan
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
     /**
      * 值
      *
      * @return {@link String}
      */
     String value() default "";
}
