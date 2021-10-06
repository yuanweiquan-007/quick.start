package quick.start.util;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @author yuanweiquan
 */
public class DateUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化Date
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        Assert.notNull(date, "要格式化的时间不能为空");
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 字符串转Date
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parser(String date) throws ParseException {
        Assert.isTrue(StringUtils.isNotEmpty(date), "要解析的时间不能为空");
        return SIMPLE_DATE_FORMAT.parse(date);
    }

    /**
     * 格式化LocalDateTime
     *
     * @param localDateTime
     * @return
     */
    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date data对象
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime localDateTime对象
     * @return Date对象
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
