package quick.start.util;

import java.io.*;

/**
 * 
 */
public abstract class BytesUtils {

    /**
     * 从对象
     *
     * @param value 值
     * @return {@link byte[]}
     */
    public static byte[] fromObject(Object value) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream tmp = new ObjectOutputStream(out);
            tmp.writeObject(value);
            tmp.flush();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 转对象
     *
     * @param value 值
     * @param clazz clazz
     * @return {@link T}
     */
    public static <T> T toObject(byte[] value, Class<T> clazz) {
        try {
            ObjectInputStream tmp = new ObjectInputStream(new ByteArrayInputStream(value));
            return (T) tmp.readObject();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 从十六进制
     *
     * @param value 值
     * @return {@link byte[]}
     */
    public static byte[] fromHex(String value) {
        int length = value.length();
        if (length % 2 == 1) {
            length++;
            value = "0" + value;
        }

        byte[] bytes = new byte[length / 2];
        for (int i = 0, j = 0; i < length; i += 2, j++) {
            bytes[j] = (byte) Integer.parseInt(value.substring(i, i + 2), 16);
        }
        return bytes;
    }

    /**
     * 转十六进制
     *
     * @param value 值
     * @return {@link String}
     */
    public static String toHex(byte[] value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            String hex = Integer.toHexString(value[i] & 0xFF);
            if (hex.length() == 1) {
                builder.append(0);
            }
            builder.append(hex);
        }
        return builder.toString();
    }

    /**
     * 从输入
     *
     * @param stream 流
     * @return {@link byte[]}
     */
    public static byte[] fromInput(InputStream stream) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        try {
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
        }
        return output.toByteArray();
    }

    /**
     * 包装
     *
     * @param value 值
     * @return {@link Byte[]}
     */
    public static Byte[] wrap(byte[] value) {
        Byte[] bytes = new Byte[value.length];
        int i = 0;
        for (byte x : value) {
            bytes[i++] = x;
        }
        return bytes;
    }

    /**
     * 打开
     *
     * @param value 值
     * @return {@link byte[]}
     */
    public static byte[] unwrap(Byte[] value) {
        byte[] bytes = new byte[value.length];
        int i = 0;
        for (byte x : value) {
            bytes[i++] = x;
        }
        return bytes;
    }

}
