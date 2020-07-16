package com.yuanwiequan.common.annotation;

import java.lang.annotation.*;

/**
 * 用来标注表名
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
     String value();
}
