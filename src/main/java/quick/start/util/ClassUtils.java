package quick.start.util;

/**
 * class帮助类
 */
public class ClassUtils {

    /**
     * 判断当前值是否指定的对象
     *
     * @param value 对象
     * @param clazz 类
     * @return 是否是指定对象
     */
    public static boolean isClass(Object value, Class<?> clazz) {
        if (value == null || clazz == null) {
            return false;
        }
        return clazz.isInstance(value);
    }

}
