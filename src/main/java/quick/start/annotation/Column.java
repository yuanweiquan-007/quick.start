package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 当对象属性和数据库属性不一致时使用
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
     String value();
}
