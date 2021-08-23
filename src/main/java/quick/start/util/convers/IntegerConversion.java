package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.ClassUtils;

import java.util.Optional;

public class IntegerConversion {

    public static Optional<Integer> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Integer) {
            return Optional.of((Integer) value);
        }
        if (ClassUtils.isClass(value, int.class)) {
            return Optional.of((int) value);
        }
        if (value instanceof Number) {
            return Optional.of(((Number) value).intValue());
        }
        if (value instanceof Boolean) {
            return convert((Boolean) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<Integer> convert(Boolean value) {
        return Optional.of(Boolean.TRUE.equals(value) ? 1 : 0);
    }

    private static Optional<Integer> convert(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
