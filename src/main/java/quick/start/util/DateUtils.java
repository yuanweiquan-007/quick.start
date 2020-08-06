package quick.start.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @author yuanweiquan
 */
public class DateUtils {

     private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

     public static String format(Date date) {
          synchronized (SIMPLE_DATE_FORMAT) {
               return SIMPLE_DATE_FORMAT.format(date);
          }
     }

     public static Date parser(String date) throws ParseException {
          synchronized (SIMPLE_DATE_FORMAT) {
               return SIMPLE_DATE_FORMAT.parse(date);
          }
     }

     public static String format(LocalDateTime localDateTime) {
          return localDateTime.format(DATE_TIME_FORMATTER);
     }

}
