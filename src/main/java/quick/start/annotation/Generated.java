package quick.start.annotation;

import java.lang.annotation.*;

/**
 * id自增时使用
 * @author yuanweiquan
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Generated {
}
