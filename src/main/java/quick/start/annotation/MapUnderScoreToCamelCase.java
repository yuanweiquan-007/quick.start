package quick.start.annotation;

import java.lang.annotation.*;

/**
 * 开启属性的驼峰命名转换
 *
 * @author yuanweiquan
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapUnderScoreToCamelCase {

}
