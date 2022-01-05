package quick.start.util.convers;

import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 *
 */
public class DateConversion {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Optional<Date> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Date) {
            return Optional.of((Date) value);
        }

        try {
            return Optional.of(DATE_TIME_FORMAT.parse(String.valueOf(value)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

}
