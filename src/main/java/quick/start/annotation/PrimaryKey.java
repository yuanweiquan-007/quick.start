package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 用来表示主键
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
     String value() default "";
}
