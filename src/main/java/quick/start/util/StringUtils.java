package quick.start.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuanweiquan
 */
public class StringUtils extends org.springframework.util.StringUtils {

     private static Pattern humpPattern = Pattern.compile("[A-Z]");

     public static String concat(String delimiter, String... strings) {
          return StreamUtils
                  .of(strings)
                  .reduce((a, b) -> a + delimiter + b)
                  .get();
     }

     /**
      * 首字母转大写
      *
      * @param context
      * @return
      */
     public static String toUpperCaseFirstOne(String context) {
          if (Character.isUpperCase(context.charAt(0))) {
               return context;
          } else {
               return (new StringBuilder()).append(Character.toUpperCase(context.charAt(0))).append(context.substring(1)).toString();
          }
     }

     /**
      * 首字母转小写
      *
      * @param context
      * @return
      */
     public static String toLowerCaseFirstOne(String context) {
          if (Character.isLowerCase(context.charAt(0))) {
               return context;
          } else {
               return (new StringBuilder()).append(Character.toLowerCase(context.charAt(0))).append(context.substring(1)).toString();
          }
     }

     /**
      * 驼峰转下划线
      *
      * @param str
      * @return
      */
     public static String humpToLine(String str) {
          Matcher matcher = humpPattern.matcher(toLowerCaseFirstOne(str));
          StringBuffer stringBuffer = new StringBuffer();
          while (matcher.find()) {
               matcher.appendReplacement(stringBuffer, "_" + matcher.group(0).toLowerCase());
          }
          matcher.appendTail(stringBuffer);
          return stringBuffer.toString();
     }

}
