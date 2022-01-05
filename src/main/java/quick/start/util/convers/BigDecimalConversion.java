package quick.start.util.convers;

import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 *
 */
public class BigDecimalConversion {

    public static Optional<BigDecimal> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof BigDecimal) {
            return Optional.of((BigDecimal) value);
        }
        if (value instanceof BigInteger) {
            return convert((BigInteger) value);
        }
        if (value instanceof Number) {
            return convert((Number) value);
        }
        if (value instanceof Boolean) {
            return convert((Boolean) value);
        }
        return convert(String.valueOf(value));
    }

    private static Optional<BigDecimal> convert(BigInteger value) {
        return Optional.of(new BigDecimal(value));
    }

    private static Optional<BigDecimal> convert(Number value) {
        return Optional.of(BigDecimal.valueOf(value.doubleValue()));
    }

    private static Optional<BigDecimal> convert(Boolean value) {
        return Optional.of(Boolean.TRUE.equals(value) ? BigDecimal.ONE : BigDecimal.ZERO);
    }

    private static Optional<BigDecimal> convert(String value) {
        try {
            return Optional.ofNullable(NumberUtils.parseNumber(value, BigDecimal.class));
        } catch (IllegalArgumentException e) {
            return Optional.of(BigDecimal.ZERO);
        }
    }

}
