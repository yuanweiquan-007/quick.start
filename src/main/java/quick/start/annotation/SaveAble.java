package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 用来控制是否保存
 * @author yuanweiquan
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveAble {
     /**
      * 值
      *
      * @return boolean
      */
     boolean value() default true;
}
