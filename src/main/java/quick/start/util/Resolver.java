package quick.start.util;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.start.entity.Entity;
import quick.start.entity.EntityMapper;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 解析器
 *
 * @author yuanweiquan
 */
public class Resolver {

    protected final Map<String, Object> data = new LinkedHashMap<>();

    public final static String DELIMITERS = "|-/#;,: ";

    private Resolver() {
    }

    private Resolver(Map<String, ?> data) {
        if (data != null) {
            data.forEach(this::set);
        }
    }

    public static Resolver of() {
        return new Resolver();
    }

    public static Resolver of(Map<String, ?> data) {
        return new Resolver(data);
    }

    public static Resolver of(String key, Object value) {
        Resolver serviceForm = new Resolver();
        serviceForm.set(key, value);
        return serviceForm;
    }

    public Resolver set(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static Resolver ofJson(String json) throws Exception {
        return of(JsonUtils.toMap(json));
    }

    public static Resolver ofXml(String xml) throws Exception {
        return of(XmlUtils.toMap(xml));
    }

    public static Resolver ofXml(String xml, String root) throws Exception {
        Map<String, Object> data = XmlUtils.toMap(xml);
        if (CollectionUtils.isEmpty(data) || !data.containsKey(root)) {
            return of();
        }
        Object tmp = data.get(root);
        if (ObjectUtils.isEmpty(tmp) && !(tmp instanceof Map)) {
            return of();
        }
        return of((Map<String, Object>) tmp);
    }

    public static <E extends Entity> Resolver ofEntity(E entity) {
        return of(EntityMapper.toMap(entity));
    }

    public <T> T get(String key) {
        Object value = getValue(key);
        if (!ObjectUtils.isEmpty(value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        T value = get(key);
        return null == value ? defaultValue : value;
    }

    public <T> void ifPresent(String key, BiConsumer<String, T> consumer) {
        T result = get(key);
        if (!ObjectUtils.isEmpty(result)) {
            consumer.accept(key, result);
        }
    }

    private Object getValue(String key) {
        Assert.hasLength(key, "key invalid");
        String[] tokenizeToStringArray = StringUtils.tokenizeToStringArray(key, DELIMITERS);
        Map<String, Object> currentData = this.data;
        for (int i = 0; i < tokenizeToStringArray.length; i++) {
            String token = tokenizeToStringArray[i];
            if (!currentData.containsKey(token)) {
                return null;
            }
            if (i == tokenizeToStringArray.length - 1) {
                return currentData.get(token);
            } else {
                Object currentValue = currentData.get(token);
                if (Map.class.isInstance(currentValue)) {
                    currentData = (Map<String, Object>) currentValue;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public <T> List<T> getList(String key) {
        List<T> container = new ArrayList<>();
        Object value = get(key);
        if (!ObjectUtils.isEmpty(value)) {
            if (value instanceof Collection) {
                container.addAll((Collection<? extends T>) value);
            } else {
                container.add((T) value);
            }
        }
        return container;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
