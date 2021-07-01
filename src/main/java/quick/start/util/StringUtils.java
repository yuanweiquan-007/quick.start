package quick.start.util;

import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuanweiquan
 */
public class StringUtils extends org.springframework.util.StringUtils {

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");

    public static Boolean isNotEmpty(@Nullable Object str) {
        return !isEmpty(str);
    }

    public static String concat(String delimiter, String... strings) {
        return StreamUtils
                .of(strings)
                .reduce((a, b) -> a + delimiter + b)
                .get();
    }

    /**
     * 首字母转大写
     *
     * @param str 字符串
     * @return 首字母大写字符串
     */
    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 首字母转小写
     *
     * @param str 字符串
     * @return 首字母小写字符串
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 驼峰转下划线
     *
     * @param str 字符串
     * @return 驼峰字符串
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
