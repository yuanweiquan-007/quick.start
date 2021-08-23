package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.ClassUtils;

import java.util.Optional;

public class FloatConversion {

    public static Optional<Float> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Float) {
            return Optional.of((Float) value);
        }
        if (ClassUtils.isClass(value, float.class)) {
            return convert((float) value);
        }
        if (value instanceof Number) {
            return convert((Number) value);
        }
        if (value instanceof Boolean) {
            return convert((Boolean) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<Float> convert(float value) {
        return Optional.of(Float.valueOf(value));
    }

    private static Optional<Float> convert(Number value) {
        return Optional.of(value.floatValue());
    }

    private static Optional<Float> convert(Boolean value) {
        return Optional.of(Boolean.TRUE.equals(value) ? 1F : 0F);
    }

    private static Optional<Float> convert(String value) {
        try {
            return Optional.of(Float.valueOf(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
