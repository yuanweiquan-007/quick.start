package quick.start.util.convers;

import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DateConversion {

    public static final InstantConversion INSTANT = new InstantConversion();

    public static Optional<Date> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Date) {
            return Optional.of((Date) value);
        }
        return INSTANT.convert(value).map(instant -> new Date(instant.minusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli()));
    }

}
