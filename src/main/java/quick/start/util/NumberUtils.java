package quick.start.util;

import java.util.function.Predicate;

/**
 * 数字帮助类
 */
public class NumberUtils {

    /**
     * 判断当前值是否number
     *
     * @param value 对象
     * @return 是否是数字
     */
    public static boolean isNumber(Object value) {
        return isNumber(value, Number.class);
    }

    /**
     * 判断当前值是否指定类型的number
     *
     * @param value 要判断的对象
     * @param clazz 类
     * @return 是否是数字
     */
    public static <T extends Number> boolean isNumber(Object value, Class<T> clazz) {
        return isNumber(value, clazz, null);
    }

    /**
     * 判断当前值是否指定类型的number
     *
     * @param value    值
     * @param clazz    对象
     * @param callback 回调
     * @return 是否是数字
     */
    public static <T extends Number> boolean isNumber(Object value, Class<T> clazz, Predicate<Number> callback) {
        try {
            T number = org.springframework.util.NumberUtils.parseNumber(String.valueOf(value), clazz);
            return callback == null || callback.test(number);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
