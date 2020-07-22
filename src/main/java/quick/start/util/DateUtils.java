package quick.start.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

     private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

     public static String format(Date date) {
          return simpleDateFormat.format(date);
     }

     public static Date parser(String date) throws ParseException {
          return simpleDateFormat.parse(date);
     }

     public static String format(LocalDateTime localDateTime) {
          return localDateTime.format(dateTimeFormatter);
     }

}
