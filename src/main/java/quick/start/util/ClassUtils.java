package quick.start.util;

public class ClassUtils {

    /**
     * 判断当前值是否指定的对象
     *
     * @param value
     * @param clazz
     * @return
     */
    public static boolean isClass(Object value, Class<?> clazz) {
        if (value == null || clazz == null) {
            return false;
        }
        return clazz.isInstance(value);
    }

}
