package quick.start.util;

import java.util.function.Predicate;

public class NumberUtils {

    /**
     * 判断当前值是否number
     *
     * @param value
     * @return
     */
    public static boolean isNumber(Object value) {
        return isNumber(value, Number.class);
    }

    /**
     * 判断当前值是否指定类型的number
     *
     * @param value
     * @param clazz
     * @return
     */
    public static <T extends Number> boolean isNumber(Object value, Class<T> clazz) {
        return isNumber(value, clazz, null);
    }

    /**
     * 判断当前值是否指定类型的number
     *
     * @param value
     * @param clazz
     * @return
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
