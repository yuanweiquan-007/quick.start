package quick.start.entity;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import quick.start.util.DateUtils;
import quick.start.util.JsonUtils;
import quick.start.util.StreamUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanweiquan
 */
@Slf4j
public class EntityMapper {

    private static final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

    public static <T extends Entity, E extends Entity> E convert(T entity, Class<E> clazz) throws Exception {
        return toEntity(toMap(entity), clazz);
    }

    public static <T extends Entity> Map<String, Object> toMap(T entity) {
        Map<String, Object> map = new HashMap<>(16);
        if (ObjectUtils.isEmpty(entity)) {
            return map;
        }
        try {
            return JsonUtils.toMap(JsonUtils.toJson(entity));
        } catch (Exception ex) {
            log.error("{} to map exception", entity, ex);
            return new HashMap<>();
        }
    }

    public static <T extends Entity> List<Map<String, Object>> toMapList(List<T> entitys) {
        return StreamUtils
                .of(entitys)
                .map(EntityMapper::toMap)
                .collect(Collectors.toList());
    }

    public static <T> T toEntity(final Map<String, Object> map, Class<T> clazz) throws Exception {
        T bean = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> lowerKeyMap = convertLowerKey(map);
        for (Field field : fields) {
            try {
                String lowerCaseFieldName = field.getName().toLowerCase();
                Object fieldValue = lowerKeyMap.get(lowerCaseFieldName);
                if (ObjectUtils.isEmpty(fieldValue)) {
                    continue;
                }
                field.setAccessible(true);
                if (!lowerKeyMap.containsKey(lowerCaseFieldName)) {
                    continue;
                }
                String simpleClassName = field.getType().getSimpleName();
                if (simpleClassName.equals(Date.class.getSimpleName())) {
                    field.set(bean, DateUtils.parser(String.valueOf(fieldValue)));
                } else if (simpleClassName.equals(Integer.class.getSimpleName())) {
                    field.set(bean, Integer.parseInt(String.valueOf(fieldValue)));
                } else if (simpleClassName.equals(Long.class.getSimpleName())) {
                    field.set(bean, Long.parseLong(String.valueOf(fieldValue)));
                } else {
                    field.set(bean, fieldValue);
                }
            } catch (Exception ex) {
                log.error("parse " + field.getName() + " exception", ex);
            }
        }
        return bean;
    }

    /**
     * 将Map的key值转换成小写
     *
     * @param map
     * @return
     */
    private static Map<String, Object> convertLowerKey(Map<String, Object> map) {
        Map<String, Object> lowerKeyMap = new HashMap<>(16);
        map.forEach((key, value) -> {
            lowerKeyMap.put(key.toLowerCase().replace("_", ""), value);
        });
        return lowerKeyMap;
    }

    public static <T> List<T> toEntityList(List<Map<String, Object>> list, Class<T> clazz) throws Exception {
        return StreamUtils
                .of(list)
                .map(map -> {
                    try {
                        return toEntity(map, clazz);
                    } catch (Exception e) {
                        logger.info("{} convert to {} fail", map, clazz, e);
                        return null;
                    }
                })
                .filter(x -> !ObjectUtils.isEmpty(x))
                .collect(
                        Collectors.toList()
                );
    }


}
