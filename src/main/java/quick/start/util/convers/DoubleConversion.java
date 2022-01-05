package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.ClassUtils;

import java.util.Optional;

/**
 *
 */
public class DoubleConversion {

    public static Optional<Double> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Double) {
            return Optional.of((Double) value);
        }
        if (ClassUtils.isClass(value, double.class)) {
            return convert((double) value);
        }
        if (value instanceof Number) {
            return convert((Number) value);
        }
        if (value instanceof Boolean) {
            return convert((Boolean) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<Double> convert(double value) {
        return Optional.of(Double.valueOf(value));
    }

    private static Optional<Double> convert(Number value) {
        return Optional.of(value.doubleValue());
    }

    private static Optional<Double> convert(Boolean value) {
        return Optional.of(Boolean.TRUE.equals(value) ? 1D : 0D);
    }

    private static Optional<Double> convert(String value) {
        try {
            return Optional.of(Double.valueOf(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}
