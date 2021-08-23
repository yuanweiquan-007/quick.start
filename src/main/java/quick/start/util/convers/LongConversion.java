package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.ClassUtils;

import java.util.Optional;

public class LongConversion {

    public static Optional<Long> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Long) {
            return Optional.of((Long) value);
        }
        if (ClassUtils.isClass(value, long.class)) {
            return convert((long) value);
        }
        if (value instanceof Number) {
            return convert((Number) value);
        }
        if (value instanceof Boolean) {
            return convert((Boolean) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<Long> convert(long value) {
        return Optional.of(Long.valueOf(value));
    }

    private static Optional<Long> convert(Number value) {
        return Optional.of(value.longValue());
    }

    private static Optional<Long> convert(Boolean value) {
        return Optional.of(Boolean.TRUE.equals(value) ? 1L : 0L);
    }

    private static Optional<Long> convert(String value) {
        try {
            return Optional.of(Long.valueOf(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
