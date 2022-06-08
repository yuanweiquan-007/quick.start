package quick.start.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author yuanweiquan
 */
public class JsonUtils {

    private final static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private final static ObjectMapper MAPPER = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDateFormat(new SimpleDateFormat(DATE_TIME_FORMATTER));

    /**
     * 集合转json
     *
     * @param data 数据
     * @return {@link String}
     */
    public static String fromMap(Map<String, Object> data) {
        return from(data);
    }

    /**
     * @param data 数据
     * @return {@link String}
     */
    public static String from(Object data) {
        return JSONObject.toJSONString(data);
    }

    /**
     * 转map
     *
     * @param text 文本
     * @return {@link Map}
     * @throws IOException ioexception
     */
    public static Map<String, Object> toMap(String text) throws IOException {
        return to(text, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 转maplist
     *
     * @param text 文本
     * @return {@link List}
     * @throws IOException ioexception
     */
    public static List<Map<String, Object>> toMapList(String text) throws IOException {
        return to(text, new TypeReference<List<Map<String, Object>>>() {
        });
    }


    /**
     * 分组
     *
     * @param text 文本
     * @return {@link Map}
     * @throws IOException ioexception
     */
    public static Map<String, Map<String, Object>> toMapKeyed(String text) throws IOException {
        return to(text, new TypeReference<Map<String, Map<String, Object>>>() {
        });
    }

    /**
     * 分组
     *
     * @param text 文本
     * @return {@link Map}
     * @throws IOException ioexception
     */
    public static Map<String, List<Map<String, Object>>> toMapGrouped(String text) throws IOException {
        return to(text, new TypeReference<Map<String, List<Map<String, Object>>>>() {
        });
    }

    /**
     * 转
     *
     * @param text      文本
     * @param reference 参考
     * @return {@link E}
     * @throws IOException ioexception
     */
    public static <E> E to(String text, TypeReference<E> reference) throws IOException {
        return MAPPER.readValue(text, reference);
    }

    /**
     * json转对象
     *
     * @param text  文本
     * @param clazz clazz
     * @return {@link E}
     */
    public static <E> E to(String text, Class<E> clazz) {
        try {
            return MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 是否是json
     *
     * @param text 文本
     * @return boolean
     */
    public static boolean is(String text) {
        try {
            return MAPPER.readTree(text) != null;
        } catch (IOException e) {
            return false;
        }
    }

}
