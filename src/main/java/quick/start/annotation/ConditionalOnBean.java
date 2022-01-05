package quick.start.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author yuanweiquan
 * 多条件必须多满足才为true
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnBeanCondition.class)
public @interface ConditionalOnBean {

     /**
      * 值
      *
      * @return {@link Class}
      */
     Class<?>[] value() default {};

}
