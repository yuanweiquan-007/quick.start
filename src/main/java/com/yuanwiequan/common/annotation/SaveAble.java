package com.yuanwiequan.common.annotation;

import java.lang.annotation.*;

/**
 * 用来控制是否保存
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveAble {
}
