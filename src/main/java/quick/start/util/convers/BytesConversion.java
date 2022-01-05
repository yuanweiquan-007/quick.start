package quick.start.util.convers;

import org.springframework.util.ObjectUtils;
import quick.start.util.BytesUtils;
import quick.start.util.ClassUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 *
 */
public class BytesConversion {

    public Optional<Byte[]> convert(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return Optional.empty();
        }
        if (value instanceof Byte[]) {
            return Optional.of((Byte[]) value);
        }
        if (ClassUtils.isClass(value, byte[].class)) {
            return convert((byte[]) value);
        }
        if (value instanceof CharSequence) {
            return convert((CharSequence) value);
        }
        if (value instanceof InputStream) {
            return convert((InputStream) value);
        }
        return convert(BytesUtils.fromObject(value));
    }

    public Optional<Byte[]> convert(byte[] value) {
        return Optional.of(BytesUtils.wrap(value));
    }

    public Optional<Byte[]> convert(CharSequence value) {
        return convert(value.toString().getBytes());
    }

    public Optional<Byte[]> convert(InputStream value) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        try {
            while (-1 != (n = value.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
            return Optional.empty();
        }
        return convert(output.toByteArray());
    }

}
