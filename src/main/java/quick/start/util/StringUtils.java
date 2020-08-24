package quick.start.util;

/**
 * @author yuanweiquan
 */
public class StringUtils extends org.springframework.util.StringUtils {

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

}
