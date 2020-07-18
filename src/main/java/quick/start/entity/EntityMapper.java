package quick.start.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.ObjectUtils;
import quick.start.util.StreamUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityMapper {

     private static final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

     public static <T extends Entity> Map<String, Object> toMap(T entity) {
          Map<String, Object> map = new HashMap<>();
          if (!ObjectUtils.isEmpty(entity)) {
               BeanMap beanMap = BeanMap.create(entity);
               for (Object key : beanMap.keySet()) {
                    map.put(String.valueOf(key), beanMap.get(key));
               }
          }
          return map;
     }

     public static <T extends Entity> List<Map<String, Object>> toMapList(List<T> entitys) {
          return StreamUtils
                  .of(entitys)
                  .map(entity -> toMap(entity))
                  .collect(Collectors.toList());
     }

     public static <T> T toEntity(Map<String, Object> map, Class<T> clazz) throws Exception {
          T bean = clazz.newInstance();
          BeanMap beanMap = BeanMap.create(bean);
          beanMap.putAll(map);
          return bean;
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
