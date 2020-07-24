package quick.start.util;

public class StringUtils extends org.springframework.util.StringUtils {

     public static String concat(String delimiter, String... strings) {
          return StreamUtils
                  .of(strings)
                  .reduce((a,b) -> a + delimiter + b)
                  .get();
     }

}
