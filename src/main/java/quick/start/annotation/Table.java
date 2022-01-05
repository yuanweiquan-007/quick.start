package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 用来标注表名
 * @author yuanweiquan
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
     /**
      * 值
      *
      * @return {@link String}
      */
     String value();
}
