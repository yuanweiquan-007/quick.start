package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.start.util.BytesUtils;

import java.io.InputStream;
import java.util.Optional;

public class StringConversion {

    public static final BytesConversion BYTES = new BytesConversion();

    public static Optional<String> convert(Object value) {
        return convert(value, true, false);
    }

    private static Optional<String> convert(Object value, boolean allowEmpty, boolean trim) {
        //若为空
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof InputStream) {
            value = BYTES.convert((InputStream) value).get();
        }
        if (value instanceof Byte[]) {
            value = BytesUtils.unwrap((Byte[]) value);
        }
        //若为二进制数组
        if (value instanceof byte[]) {
            value = new String((byte[]) value);
        }
        //若要去除前后空格
        if (value instanceof CharSequence && trim) {
            value = value.toString().trim();
        }
        //若允许空字符串
        if (StringUtils.isEmpty(value) && allowEmpty) {
            return Optional.of(String.valueOf(value));
        }
        return Optional.of(String.valueOf(value));
    }

}
