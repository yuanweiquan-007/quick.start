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

    /**
     * 格式化Date
     *
     * @param date 时间
     * @return 格式化后的结果
     */
    public static String format(Date date) {
        Assert.notNull(date, "要格式化的时间不能为空");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 字符串转Date
     *
     * @param date 字符串时间
     * @return 时间
     * @throws ParseException 解析异常
     */
    public static Date parser(String date) throws ParseException {
        Assert.isTrue(StringUtils.isNotEmpty(date), "要解析的时间不能为空");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }

    /**
     * 格式化LocalDateTime
     *
     * @param localDateTime localDateTime
     * @return 字符串时间
     */
    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * date 转 LocalDateTime
     *
     * @param date data对象
     * @return localDateTime对象
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
