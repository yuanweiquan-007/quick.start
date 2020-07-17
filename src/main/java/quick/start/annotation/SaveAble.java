package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 用来控制是否保存
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveAble {
     boolean value() default true;
}
