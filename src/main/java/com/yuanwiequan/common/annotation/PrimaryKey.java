package com.yuanwiequan.common.annotation;

import java.lang.annotation.*;

/**
 * 用来表示主键
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {
}
