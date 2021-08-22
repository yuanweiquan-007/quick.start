package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.ClassUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BooleanConversion {

    private static final List<String> PRIVATIVES = Arrays.asList("false", "0", "");

    public static Optional<Boolean> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.of(Boolean.FALSE);
        }
        if (value instanceof Boolean) {
            return Optional.of((Boolean) value);
        }
        if (ClassUtils.isClass(value, boolean.class)) {
            return convert((boolean) value);
        }
        if (value instanceof Number) {
            return convert((Number) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<Boolean> convert(boolean value) {
        return Optional.of(Boolean.valueOf(value));
    }

    private static Optional<Boolean> convert(Number value) {
        return Optional.of(value.doubleValue() == 0.0 ? Boolean.FALSE : Boolean.TRUE);
    }

    private static Optional<Boolean> convert(String value) {
        return Optional.of(PRIVATIVES.contains(value) ? Boolean.FALSE : Boolean.TRUE);
    }

}
